package io.bsamartins.spqr.federation

import com.apollographql.federation.graphqljava._Entity
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import org.slf4j.LoggerFactory

abstract class FederationDataFetcher : DataFetcher<List<*>> {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun get(environment: DataFetchingEnvironment): List<*> {
        val representations = getRepresentations(environment)
        logger.info("Representations: {}", representations)
        return get(environment, representations)
    }

    protected fun getRepresentations(environment: DataFetchingEnvironment): List<Map<String, *>> {
        return environment.getArgument(_Entity.argumentName)
    }

    abstract fun get(environment: DataFetchingEnvironment, representations: List<Map<String, *>>): List<*>
}