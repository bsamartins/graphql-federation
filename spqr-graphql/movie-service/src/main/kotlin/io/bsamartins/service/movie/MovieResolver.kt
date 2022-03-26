package io.bsamartins.service.movie

import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.stereotype.Component


@Component
@GraphQLApi
class MovieResolver {

    private val movies = listOf(
        MovieModel(id = 1, "Eyes Wide Shut", actors = listOf(1, 2))
    )

    @GraphQLQuery
    fun findMovies(): List<MovieModel> = movies
}