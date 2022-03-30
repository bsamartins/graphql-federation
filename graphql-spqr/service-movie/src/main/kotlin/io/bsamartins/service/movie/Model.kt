package io.bsamartins.service.movie

import io.bsamartins.movie.Movie
import io.bsamartins.spqr.federation.annotation.FederationExtends
import io.bsamartins.spqr.federation.annotation.FederationExternal
import io.bsamartins.spqr.federation.annotation.FederationKey

@FederationKey(fields = "movieId")
data class MovieModel(val movieId: Int, val title: String, val director: DirectorModel)

@FederationExtends
@FederationKey(fields = "directorId")
data class DirectorModel(
    @FederationExternal
    val directorId: Int,
)

fun Movie.toModel(): MovieModel = MovieModel(
    movieId = this.id,
    title = this.title,
    director = DirectorModel(directorId = this.directorId)
)