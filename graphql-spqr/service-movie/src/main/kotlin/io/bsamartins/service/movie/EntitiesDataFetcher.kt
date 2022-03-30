package io.bsamartins.service.movie

import graphql.schema.DataFetchingEnvironment
import io.bsamartins.movie.MovieService
import io.bsamartins.spqr.federation.FederationDataFetcher
import org.slf4j.LoggerFactory

class EntitiesDataFetcher(val movieService: MovieService) : FederationDataFetcher() {

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
            "MovieModel" -> {
                val directorId = representation["movieId"]!!.toString().toInt()
                movieService.findById(directorId)?.toModel()
            }
            else -> {
                logger.error("No resolver for type: '{}'", typeName)
                null
            }
        }
    }
}