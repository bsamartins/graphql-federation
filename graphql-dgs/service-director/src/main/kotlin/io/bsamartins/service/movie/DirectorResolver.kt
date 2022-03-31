package io.bsamartins.service.movie

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsEntityFetcher
import com.netflix.graphql.dgs.DgsQuery
import io.bsamartins.director.Director
import io.bsamartins.director.DirectorService
import io.bsamartins.service.director.model.types.DirectorModel


@DgsComponent
class DirectorResolver(private val directorService: DirectorService) {

    @DgsQuery
    fun findDirectors(): List<DirectorModel> = directorService.findAll().map { it.toModel() }

    @DgsEntityFetcher(name = "DirectorModel")
    fun director(values: Map<String, Any?>): DirectorModel? {
        val directorId = values["directorId"] as Int
        return directorService.findById(directorId)?.toModel()
    }
}

private fun Director.toModel(): DirectorModel {
    return DirectorModel(
        directorId = this.id!!,
        name = this.name,
    )
}