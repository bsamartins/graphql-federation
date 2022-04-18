package io.bsamartins.graphql.quarkus.federation.deployment;

import io.bsamartins.graphql.quarkus.federation.deployment.apollo.ApolloFederation;
import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;

class GraphqlFederationQuarkusExtensionProcessor {
    private static final String FEATURE = "graphql-quarkus-federation";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

//    @BuildStep
//    AdditionalBeanBuildItem beans() {
//        return new AdditionalBeanBuildItem(Federation.class);
//    }

    @BuildStep
    AdditionalBeanBuildItem beans() {
        return new AdditionalBeanBuildItem(ApolloFederation.class);
    }
}