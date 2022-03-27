package io.bsamartins.service.movie

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import io.bsamartins.service.movie.model.types.MovieModel


@DgsComponent
class MovieResolver {

    private val movies = listOf(
        MovieModel(id = 1, "Eyes Wide Shut")
    )

    @DgsQuery
    fun findMovies(): List<MovieModel> = movies
}