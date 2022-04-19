package io.bsamartins.service.movie

import io.bsamartins.movie.MovieService
import io.bsamartins.spqr.federation.annotation.DataFetcher
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class MovieResolver(private val movieService: MovieService) {

    @GraphQLQuery
    fun findMovies(): List<MovieModel> = movieService.findAll().map { it.toModel() }

    @GraphQLQuery
    fun findMovieById(@GraphQLArgument(name = "id") id: Int): MovieModel? = movieService.findById(id)?.toModel()

    @DataFetcher(type = "ActorModel")
    fun movieResolver(params: Map<String, Any?>): MovieModel? {
        return findMovieById(params["movieId"] as Int)
    }
}
