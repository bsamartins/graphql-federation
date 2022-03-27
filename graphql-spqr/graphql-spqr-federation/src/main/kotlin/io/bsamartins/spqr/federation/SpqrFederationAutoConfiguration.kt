package io.bsamartins.spqr.federation

import graphql.schema.GraphQLSchema
import io.leangen.graphql.GraphQLSchemaGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpqrFederationAutoConfiguration {

    @Bean
    fun graphQLSchema(schemaGenerator: GraphQLSchemaGenerator): GraphQLSchema {
        return schemaGenerator.generate()
    }
}