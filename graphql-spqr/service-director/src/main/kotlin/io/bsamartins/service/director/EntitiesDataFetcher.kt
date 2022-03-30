package io.bsamartins.service.director

import graphql.schema.DataFetchingEnvironment
import io.bsamartins.director.DirectorService
import io.bsamartins.spqr.federation.FederationDataFetcher
import org.slf4j.LoggerFactory

class EntitiesDataFetcher(val directorService: DirectorService) : FederationDataFetcher() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun get(environment: DataFetchingEnvironment, representations: List<Map<String, *>>): List<*> {
        return resolve(environment, representations)
    }

    private fun resolve(environment: DataFetchingEnvironment, entities: List<Map<String, *>>): List<Any?> {
        return entities.map { resolveEntity(environment, it) }
    }

    private fun resolveEntity(environment: DataFetchingEnvironment, representation: Map<String, *>): Any? {
        val typeName = representation["__typename"] as String
        logger.info("Resolving type: {}", typeName)
        val type = environment.graphQLSchema.getType(typeName)
        logger.info("Schema type: {}", type)

        return when(typeName) {
            "DirectorModel" -> {
                val directorId = representation["directorId"]!!.toString().toInt()
                directorService.findById(directorId)?.toModel()
            }
            else -> {
                logger.error("No resolver for type: '{}'", typeName)
                null
            }
        }
    }
}