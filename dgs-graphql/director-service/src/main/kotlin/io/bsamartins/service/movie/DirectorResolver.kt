package io.bsamartins.service.movie

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsEntityFetcher
import com.netflix.graphql.dgs.DgsQuery
import io.bsamartins.service.director.model.types.DirectorModel


@DgsComponent
class DirectorResolver {

    private val directors = listOf(
        DirectorModel(directorId = 1, "Stanly Kubrick")
    ).associateBy { it.directorId }

    @DgsQuery
    fun findDirectors(): List<DirectorModel> = directors.values.toList()

    @DgsEntityFetcher(name = "DirectorModel")
    fun director(values: Map<String, Any?>): DirectorModel? {
        val directorId = values["directorId"] as Int
        return directors[directorId]
    }
}