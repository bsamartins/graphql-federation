package io.bsamartins.graphql.quarkus.federation.deployment;

import io.quarkus.builder.item.SimpleBuildItem;

import java.util.ArrayList;
import java.util.List;

class EntitiesBuildItem extends SimpleBuildItem {
    private final List<String> entities = new ArrayList<>();

    public void add(String name) {
        entities.add(name);
    }

    public List<String> getEntities() {
        return entities;
    }
}
