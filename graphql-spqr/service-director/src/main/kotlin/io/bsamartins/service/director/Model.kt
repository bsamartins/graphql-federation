package io.bsamartins.service.director

import io.bsamartins.spqr.federation.annotation.FederationKey

@FederationKey(fields = "directorId")
data class DirectorModel(val directorId: Int, val title: String)