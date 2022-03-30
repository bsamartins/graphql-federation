package io.bsamartins.service.director

import io.bsamartins.director.DirectorService
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.stereotype.Component


@Component
@GraphQLApi
class DirectorResolver(val directorService: DirectorService) {

    @GraphQLQuery
    fun findDirectors(): List<DirectorModel> = directorService.findAll()
        .map { it.toModel() }

    @GraphQLQuery
    fun findDirectorById(@GraphQLArgument(name = "id") id: Int): DirectorModel? = directorService.findById(id)?.toModel()
}
