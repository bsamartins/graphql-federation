package io.bsamartins.service.movie

import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.stereotype.Component


@Component
@GraphQLApi
class MovieResolver {

    private val movies = listOf(
        MovieModel(movieId = 1, "Eyes Wide Shut", director = DirectorModel(directorId = 1)),
        MovieModel(movieId = 2, "Top Gun", director = DirectorModel(directorId = 2)),
    )

    @GraphQLQuery
    fun findMovies(): List<MovieModel> = movies
}