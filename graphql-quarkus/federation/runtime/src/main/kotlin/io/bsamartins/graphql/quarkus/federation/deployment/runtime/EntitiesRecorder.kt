package io.bsamartins.graphql.quarkus.federation.deployment.runtime

import io.quarkus.runtime.annotations.Recorder
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext

@Recorder
class EntitiesRecorder {
    fun createHandler(entities: List<String>?): Handler<RoutingContext> {
        return DevEndpointHandler(entities)
    }
}
