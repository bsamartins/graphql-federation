package io.bsamartins.service.movie

import com.expediagroup.graphql.generator.federation.execution.FederatedTypeResolver
import com.expediagroup.graphql.server.operations.Query
import graphql.schema.DataFetchingEnvironment
import io.bsamartins.movie.MovieService
import org.springframework.stereotype.Component

@Component
class MovieResolver(private val movieService: MovieService) : Query {

    fun findMovies(): List<MovieModel> = movieService.findAll().map { it.toModel() }

    fun findMovieById(id: Int): MovieModel? = movieService.findById(id)?.toModel()
}

@Component
class MovieModelFederatedResolver(private val movieService: MovieService) : FederatedTypeResolver<MovieModel> {
    override val typeName: String = "MovieModel"

    override suspend fun resolve(
        environment: DataFetchingEnvironment,
        representations: List<Map<String, Any>>
    ): List<MovieModel?> {
        return representations.map { it["movieId"] as Int }
            .map { movieService.findById(it)?.toModel() }
    }
}
