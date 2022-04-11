package io.bsamartins.graphql.quarkus.federation.deployment.runtime

import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import java.lang.StringBuilder
import java.util.function.Consumer

class DevEndpointHandler(private val entities: List<String>? = null) : Handler<RoutingContext> {

    override fun handle(routingContext: RoutingContext) {
        val out = StringBuilder()
        out.append("entities:\n")
        entities?.forEach(Consumer { entity -> out.append("> ").append(entity).append("\n") })
            ?: out.append("none")
        routingContext.response().end(out.toString())
    }
}
