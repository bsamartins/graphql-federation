package io.bsamartins.service.movie

import com.expediagroup.graphql.generator.federation.directives.FieldSet
import com.expediagroup.graphql.generator.federation.directives.KeyDirective
import io.bsamartins.director.Director

@KeyDirective(fields = FieldSet("directorId"))
data class DirectorModel(val directorId: Int, val name: String)

fun Director.toModel(): DirectorModel = DirectorModel(
    directorId = this.id!!,
    name = this.name,
)
