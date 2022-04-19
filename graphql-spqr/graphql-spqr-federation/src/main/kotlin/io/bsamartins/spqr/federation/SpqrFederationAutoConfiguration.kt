package io.bsamartins.spqr.federation

import com.apollographql.federation.graphqljava.Federation
import com.apollographql.federation.graphqljava.FederationDirectives
import com.fasterxml.jackson.databind.ObjectMapper
import graphql.ExecutionResult
import graphql.execution.instrumentation.Instrumentation
import graphql.execution.instrumentation.InstrumentationContext
import graphql.execution.instrumentation.SimpleInstrumentation
import graphql.execution.instrumentation.SimpleInstrumentationContext
import graphql.execution.instrumentation.parameters.InstrumentationExecuteOperationParameters
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters
import graphql.introspection.Introspection
import graphql.schema.*
import graphql.schema.idl.SchemaPrinter
import io.bsamartins.spqr.SimpleNameClassTypeResolver
import io.leangen.graphql.GraphQLSchemaGenerator
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.getBeansWithAnnotation
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.AnnotationUtils
import kotlin.reflect.KFunction
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.jvm.javaMethod
import io.bsamartins.spqr.federation.annotation.DataFetcher as DataFetcherAnnotation

@Configuration
class SpqrFederationAutoConfiguration {

    @Bean
    fun dataFetcher(applicationContext: ApplicationContext): FederationDataFetcher {
        val dataFetchersMap = mutableMapOf<String, DataFetcherExecutor>()
        applicationContext.getBeansWithAnnotation<GraphQLApi>().forEach { (name, bean) ->
            bean::class.memberFunctions.forEach members@{ function ->
                val ann = AnnotationUtils.findAnnotation(function.javaMethod, DataFetcherAnnotation::class.java)
                    ?: return@members
                val attributes = AnnotationUtils.getAnnotationAttributes(ann)
                val type = attributes["type"] as String
                dataFetchersMap[type] = DataFetcherExecutor(instance = bean, type = type, function = function)
            }
        }

        dataFetchersMap.forEach { t, u -> println("$t=$u") }

        return object : FederationDataFetcher() {

            private val logger = LoggerFactory.getLogger(this::class.java)

            override fun get(environment: DataFetchingEnvironment, representations: List<Map<String, *>>): List<*> {
                return representations.map { resolve(environment, it) }
            }

            private fun resolve(environment: DataFetchingEnvironment, representation: Map<String, *>): Any? {
                val typeName = representation["__typename"] as String
                logger.info("Resolving type: {}", typeName)
                val type = environment.graphQLSchema.getType(typeName)
                logger.info("Schema type: {}", type)

                val executor = dataFetchersMap[typeName]
                return if (executor != null) {
                    executor.execute(representation)
                } else {
                    logger.error("No resolver for type: '{}'", typeName)
                    null
                }
            }
        }
    }

    @Bean
    fun graphQLSchema(
        schemaGenerator: GraphQLSchemaGenerator,
        federationDataFetcher: FederationDataFetcher,
        typeResolver: TypeResolver,
    ): GraphQLSchema {
        val schema = schemaGenerator.generate()

        // Add new definitions to schema
        val newSchema = GraphQLSchema.newSchema(schema)
            .registerDirectives(schema)
            .additionalDirectives(FederationDirectives.allDirectives)
            .build()

        val federationSchema = Federation.transform(newSchema)
            .fetchEntities(federationDataFetcher)
            .resolveEntityType(typeResolver)
            .build()
        printSchema(federationSchema)
        return federationSchema
    }

    private fun GraphQLSchema.Builder.registerDirectives(schema: GraphQLSchema): GraphQLSchema.Builder {
        // UNREPRESENTABLE scalar
        val unrepresentableScalar = schema.getType("UNREPRESENTABLE") as GraphQLScalarType

        // _mappedType directive definition
        val mappedTypeDirective = GraphQLDirective.newDirective()
            .name("_mappedType")
            .description("")
            .validLocation(Introspection.DirectiveLocation.OBJECT)
            .argument(
                GraphQLArgument.newArgument()
                    .name("type")
                    .description("")
                    .type(unrepresentableScalar)
                    .build()
            )
            .build()

        // _mappedOperation directive definition
        val mappedOperationDirective: GraphQLDirective = GraphQLDirective.newDirective()
            .name("_mappedOperation")
            .description("")
            .validLocation(Introspection.DirectiveLocation.FIELD_DEFINITION)
            .argument(
                GraphQLArgument.newArgument()
                    .name("operation")
                    .description("")
                    .type(unrepresentableScalar)
                    .build()
            )
            .build()

        // _mappedInputField directive definition
        val mappedInputFieldDirective: GraphQLDirective = GraphQLDirective.newDirective()
            .name("_mappedInputField")
            .description("")
            .validLocation(Introspection.DirectiveLocation.INPUT_FIELD_DEFINITION)
            .argument(
                GraphQLArgument.newArgument()
                    .name("inputField")
                    .description("")
                    .type(unrepresentableScalar)
                    .build()
            )
            .build()

        return this.additionalDirective(mappedTypeDirective)
            .additionalDirective(mappedOperationDirective)
            .additionalDirective(mappedInputFieldDirective)
    }

    @Bean
    fun loggingInstrumentation(objectMapper: ObjectMapper): Instrumentation {
        return object : SimpleInstrumentation() {
            private val logger = LoggerFactory.getLogger(this::class.java)
            override fun beginExecution(parameters: InstrumentationExecutionParameters): InstrumentationContext<ExecutionResult> {
                logger.info(
                    "Query: {}",
                    parameters.executionInput.query
                        .replace("\n", "")
                        .replace("\r", "")
                )
                return SimpleInstrumentationContext.whenCompleted { res, t ->
                    if (t == null) {
                        logger.info("Execution result: {}", res)
                    }
                }
            }
            override fun beginExecuteOperation(parameters: InstrumentationExecuteOperationParameters): InstrumentationContext<ExecutionResult> {
                return super.beginExecuteOperation(parameters)
            }
        }
    }

    @Bean
    @ConditionalOnMissingBean(TypeResolver::class)
    fun typeResolver(): TypeResolver {
        return SimpleNameClassTypeResolver()
    }

    private fun printSchema(schema: GraphQLSchema) {
        println("Schema With Federation >>>")
        val printedSchema = SchemaPrinter( // Tweak the options accordingly
            SchemaPrinter.Options.defaultOptions().includeDirectives(true)
        ).print(schema)
        println(printedSchema)
        println(" >>>>>>>>>>>    ")
    }
}

data class DataFetcherExecutor(val instance: Any, val type: String, val function: KFunction<*>) {

    fun execute(params: Map<String, Any?>): Any? {
        return function.call(instance, params)
    }
}
