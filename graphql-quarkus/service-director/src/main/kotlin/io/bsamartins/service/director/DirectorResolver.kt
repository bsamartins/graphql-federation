package io.bsamartins.service.director

import io.bsamartins.director.DirectorService
import org.eclipse.microprofile.graphql.GraphQLApi
import org.eclipse.microprofile.graphql.Query

@GraphQLApi
class DirectorResolver(private val directorService: DirectorService) {

    @Query("findDirectors")
    fun findDirectors(): List<DirectorModel> = directorService.findAll()
        .map { it.toModel() }
}
