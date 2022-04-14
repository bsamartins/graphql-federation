package io.bsamartins.graphql.quarkus.federation.deployment.runtime

import io.quarkus.runtime.annotations.Recorder
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext

@Recorder
open class EntitiesRecorder {
    open fun createHandler(entities: List<String>): Handler<RoutingContext> {
        println("EntitiesRecorder -> $entities")
        return DevEndpointHandler(entities)
    }
}
