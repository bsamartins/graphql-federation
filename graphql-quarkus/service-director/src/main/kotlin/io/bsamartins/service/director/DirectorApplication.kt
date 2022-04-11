package io.bsamartins.service.director

import io.bsamartins.director.DirectorService
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces

@ApplicationScoped
class DirectorApplication {

    @Produces
    fun directorService(): DirectorService = DirectorService()
}
