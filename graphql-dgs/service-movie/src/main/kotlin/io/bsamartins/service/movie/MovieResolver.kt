package io.bsamartins.service.movie

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import io.bsamartins.movie.Movie
import io.bsamartins.movie.MovieService
import io.bsamartins.service.movie.model.types.DirectorModel
import io.bsamartins.service.movie.model.types.MovieModel


@DgsComponent
class MovieResolver(private val movieService: MovieService) {

    @DgsQuery
    fun findMovies(): List<MovieModel> = movieService.findAll().map { it.toModel() }

    @DgsQuery
    fun findMovieById(id: Int): MovieModel? = movieService.findById(id)?.toModel()
}

private fun Movie.toModel(): MovieModel {
    return MovieModel(
        movieId = this.id,
        title = this.title,
        director = DirectorModel(this.directorId)
    )
}