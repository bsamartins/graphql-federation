package io.bsamartins.service.movie

import io.bsamartins.movie.MovieService
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.stereotype.Component


@Component
@GraphQLApi
class MovieResolver(private val movieService: MovieService) {

    @GraphQLQuery
    fun findMovies(): List<MovieModel> = movieService.findAll().map { it.toModel() }
}