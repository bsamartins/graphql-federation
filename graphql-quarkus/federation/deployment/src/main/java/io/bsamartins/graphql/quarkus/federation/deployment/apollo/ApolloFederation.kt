package io.bsamartins.graphql.quarkus.federation.deployment.apollo

import com.apollographql.federation.graphqljava.Federation
import com.apollographql.federation.graphqljava.FederationDirectives
import graphql.schema.GraphQLSchema
import graphql.schema.GraphQLSchema.Builder
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Observes

@ApplicationScoped
class ApolloFederation {

    fun observe(@Observes builder: Builder): Builder {
        FederationDirectives.allDirectives.forEach {
            builder.additionalDirective(it)
        }
        val schema = builder.build()

        return Federation.transform(schema)
            .build()
            .let { GraphQLSchema.newSchema(it) }
    }
}
