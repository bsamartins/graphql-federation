package io.bsamartins.service.movie

import com.expediagroup.graphql.generator.federation.execution.FederatedTypeResolver
import com.expediagroup.graphql.server.operations.Query
import graphql.schema.DataFetchingEnvironment
import io.bsamartins.director.DirectorService
import org.springframework.stereotype.Component

@Component
class DirectorResolver(private val directorService: DirectorService) : Query {

    fun findDirectors(): List<DirectorModel> = directorService.findAll().map { it.toModel() }

    fun findDirectorById(id: Int): DirectorModel? = directorService.findById(id)?.toModel()
}

@Component
class DirectorModelFederatedResolver(private val directorService: DirectorService) : FederatedTypeResolver<DirectorModel> {
    override val typeName: String = "DirectorName"

    override suspend fun resolve(
        environment: DataFetchingEnvironment,
        representations: List<Map<String, Any>>
    ): List<DirectorModel?> {
        return representations.map { it["directorId"] as Int }
            .map { directorService.findById(it)?.toModel() }
    }
}
