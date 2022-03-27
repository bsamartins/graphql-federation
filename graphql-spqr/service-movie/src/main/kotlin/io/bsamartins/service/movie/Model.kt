package io.bsamartins.service.movie

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