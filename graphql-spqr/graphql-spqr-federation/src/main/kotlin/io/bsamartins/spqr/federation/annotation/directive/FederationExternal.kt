package io.bsamartins.spqr.federation.annotation.directive

import com.apollographql.federation.graphqljava.FederationDirectives
import io.leangen.graphql.annotations.types.GraphQLDirective

@GraphQLDirective(name = FederationDirectives.externalName)
@Retention
@Target(allowedTargets = [AnnotationTarget.FIELD])
annotation class FederationExternal