package io.bsamartins.service.director

import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.stereotype.Component


@Component
@GraphQLApi
class DirectorResolver {

    private val directors = listOf(
        DirectorModel(directorId = 1, "Stanly Kubrick")
    ).associateBy { it.directorId }

    @GraphQLQuery
    fun findDirectors(): List<DirectorModel> = directors.values.toList()

//    @DgsEntityFetcher(name = "DirectorModel")
//    fun director(values: Map<String, Any?>): DirectorModel? {
//        val directorId = values["directorId"] as Int
//        return directors[directorId]
//    }
}