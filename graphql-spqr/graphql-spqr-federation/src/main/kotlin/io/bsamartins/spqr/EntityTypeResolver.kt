package io.bsamartins.spqr

import graphql.TypeResolutionEnvironment
import graphql.schema.GraphQLObjectType
import graphql.schema.TypeResolver

class SimpleNameClassTypeResolver : TypeResolver {
    override fun getType(env: TypeResolutionEnvironment): GraphQLObjectType {
        val src = env.getObject<Any>()
        return env.schema.getObjectType(src::class.simpleName)
    }
}