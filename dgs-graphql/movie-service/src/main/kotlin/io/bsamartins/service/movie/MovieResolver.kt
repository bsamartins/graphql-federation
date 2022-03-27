package io.bsamartins.service.movie

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import io.bsamartins.service.movie.model.types.DirectorModel
import io.bsamartins.service.movie.model.types.MovieModel


@DgsComponent
class MovieResolver {

    private val movies = listOf(
        MovieModel(movieId = 1, "Eyes Wide Shut", director = DirectorModel(directorId = 1)),
        MovieModel(movieId = 2, "Top Gun", director = DirectorModel(directorId = 2)),
        MovieModel(movieId = 3, "New Movie", director = null),
    )

    @DgsQuery
    fun findMovies(): List<MovieModel> = movies
}