package io.bsamartins.spqr.federation.annotation.directive

import com.apollographql.federation.graphqljava.FederationDirectives
import io.leangen.graphql.annotations.types.GraphQLDirective

@GraphQLDirective(name = FederationDirectives.extendsName)
@Retention
@Target(allowedTargets = [AnnotationTarget.CLASS])
annotation class FederationExtends