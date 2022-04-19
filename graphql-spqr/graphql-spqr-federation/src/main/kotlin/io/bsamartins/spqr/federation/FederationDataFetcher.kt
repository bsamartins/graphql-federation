package io.bsamartins.spqr.federation

import com.apollographql.federation.graphqljava._Entity
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import org.slf4j.LoggerFactory

class FederationDataFetcher(private val dataFetchersMap: Map<String, DataFetcherExecutor>) : DataFetcher<List<*>> {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun get(environment: DataFetchingEnvironment): List<*> {
        val representations = getRepresentations(environment)
        logger.info("Representations: {}", representations)
        return get(environment, representations)
    }

    protected fun getRepresentations(environment: DataFetchingEnvironment): List<Map<String, *>> {
        return environment.getArgument(_Entity.argumentName)
    }

    fun get(environment: DataFetchingEnvironment, representations: List<Map<String, *>>): List<*> {
        return representations.map { get(environment, it) }
    }

    fun get(environment: DataFetchingEnvironment, representation: Map<String, *>): Any? {
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
