package io.bsamartins.service.director

import graphql.schema.DataFetchingEnvironment
import io.bsamartins.spqr.federation.FederationDataFetcher
import org.slf4j.LoggerFactory

class EntitiesDataFetcher : FederationDataFetcher() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun get(environment: DataFetchingEnvironment, representations: List<Map<String, *>>): List<*> {
        return resolve(representations)
    }

    private fun resolve(entities: List<Map<String, *>>): List<Any?> {
        return entities.map { resolveEntity(it) }
    }

    private fun resolveEntity(representation: Map<String, *>): Any? {
        val type = representation["__typename"]
        logger.info("Resolving type: {}", type)
        return when(type) {
            "DirectorModel_" -> null
            else -> {
                logger.error("No resolver for type: '{}'", type)
                null
            }
        }
    }
}