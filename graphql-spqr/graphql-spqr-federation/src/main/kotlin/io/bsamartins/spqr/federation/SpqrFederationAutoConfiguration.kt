package io.bsamartins.spqr.federation

import com.apollographql.federation.graphqljava.Federation
import com.apollographql.federation.graphqljava.FederationDirectives
import com.apollographql.federation.graphqljava._Entity
import graphql.ExecutionResult
import graphql.execution.instrumentation.Instrumentation
import graphql.execution.instrumentation.InstrumentationContext
import graphql.execution.instrumentation.SimpleInstrumentation
import graphql.execution.instrumentation.parameters.InstrumentationExecuteOperationParameters
import graphql.introspection.Introspection
import graphql.schema.*
import graphql.schema.idl.SchemaPrinter
import io.leangen.graphql.GraphQLSchemaGenerator
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import com.apollographql.federation.graphqljava.SchemaTransformer


@Configuration
class SpqrFederationAutoConfiguration {

    @Bean
    fun graphQLSchema(schemaGenerator: GraphQLSchemaGenerator): GraphQLSchema {
        val schema = schemaGenerator.generate()

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

        // Add new definitions to schema
        val newSchema = GraphQLSchema.newSchema(schema)
            .additionalDirective(mappedTypeDirective)
            .additionalDirective(mappedOperationDirective)
            .additionalDirective(mappedInputFieldDirective)
            .build()


        val federationSchema = Federation.transform(newSchema)
            .fetchEntities(entitiesDataFetcher())
            .resolveEntityType(typeResolver())
            .build()
            .let { GraphQLSchema.newSchema(it) }
            .additionalDirectives(FederationDirectives.allDirectives)
            .build()
        printSchema(federationSchema)
        return federationSchema
    }

    @Bean
    fun loggingInstrumentation(): Instrumentation {
        return object : SimpleInstrumentation() {
            private val logger = LoggerFactory.getLogger(this::class.java)
            override fun beginExecuteOperation(parameters: InstrumentationExecuteOperationParameters): InstrumentationContext<ExecutionResult> {
                logger.info("Query: {}", parameters.executionContext.executionInput.query
                    .replace("\n", "")
                    .replace("\r", ""))
                return super.beginExecuteOperation(parameters)
            }
        }
    }

    private fun typeResolver(): TypeResolver {
        return TypeResolver {
            println("Environment -> $it")
            null
        }
    }

    private fun entitiesDataFetcher(): DataFetcher<List<*>> {
        return DataFetcher { env ->
            val representations = env.getArgument<List<String>>(_Entity.argumentName)
            println("type: $representations, ${representations::class}")
            representations.forEach { println(it) }
            emptyList<Any>()
        }
    }

    private fun createSchemaWithDirectives(schema: GraphQLSchema): GraphQLSchema {
        return Federation.transform(schema)
//            .fetchEntities { env: DataFetchingEnvironment ->
//                env.getArgument<List<Map<String, Any>>>(_Entity.argumentName)
//                .map { values: Map<String, Any> ->
////                    if ("Product" == values["__typename"]) {
////                        val upc = values["upc"]
////                        if (upc is String) {
////                            return@map productService.find(upc as String?)
////                        }
////                    }
//                    null
//                }
//        }
//    .resolveEntityType { env ->
//                val src = env.getObject<Any>()
//                if (src is Product) {
//                    return@resolveEntityType env.schema.getObjectType("Product")
//                }
//                null
//            }
        .build()
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