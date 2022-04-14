package io.bsamartins.service.movie

import io.bsamartins.movie.Movie
import io.bsamartins.spqr.federation.annotation.directive.FederationExtends
import io.bsamartins.spqr.federation.annotation.directive.FederationExternal
import io.bsamartins.spqr.federation.annotation.directive.FederationKey
import java.math.BigDecimal

@FederationKey(fields = "movieId")
data class MovieModel(val movieId: Int, val title: String, val director: DirectorModel)

@FederationExtends
@FederationKey(fields = "directorId")
data class DirectorModel(
    @FederationExternal
    val directorId: Int,
    val salary: BigDecimal,
)

fun Movie.toModel(): MovieModel = MovieModel(
    movieId = this.id,
    title = this.title,
    director = DirectorModel(
        directorId = this.director.id,
        salary = this.director.salary,
    )
)
