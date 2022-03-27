package io.bsamartins.service.movie

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import io.bsamartins.service.movie.model.types.DirectorModel
import io.bsamartins.service.movie.model.types.MovieModel


@DgsComponent
class MovieResolver {

    private val movies = listOf(
        MovieModel(movieId = 1, "Eyes Wide Shut", director = DirectorModel(directorId = 1))
    )

    @DgsQuery
    fun findMovies(): List<MovieModel> = movies
}