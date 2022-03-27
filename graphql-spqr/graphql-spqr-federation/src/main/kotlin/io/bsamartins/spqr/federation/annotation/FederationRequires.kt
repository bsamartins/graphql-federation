package io.bsamartins.spqr.federation.annotation

import com.apollographql.federation.graphqljava.FederationDirectives
import io.leangen.graphql.annotations.types.GraphQLDirective

@GraphQLDirective(name = FederationDirectives.requiresName)
@Retention
@Target(allowedTargets = [AnnotationTarget.FIELD])
annotation class FederationRequires(val fields: String)