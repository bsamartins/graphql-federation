package io.bsamartins.service.director

import io.bsamartins.director.Director
import io.bsamartins.spqr.federation.annotation.FederationKey

@FederationKey(fields = "directorId")
data class DirectorModel(val directorId: Int, val name: String)

fun Director.toModel(): DirectorModel = DirectorModel(
    directorId = this.id!!,
    name = this.name,
)