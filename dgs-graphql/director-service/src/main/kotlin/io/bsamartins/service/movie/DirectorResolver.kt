package io.bsamartins.service.movie

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import io.bsamartins.service.director.model.types.DirectorModel


@DgsComponent
class DirectorResolver {

    private val directors = listOf(
        DirectorModel(id = 1, "Stanly Kubrick")
    )

    @DgsQuery
    fun director(): List<DirectorModel> = directors
}