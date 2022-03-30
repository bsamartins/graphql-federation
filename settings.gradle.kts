rootProject.name = "graphql-federation"

include(":common:service-movie")
include(":common:service-director")

include(":graphql-dgs:service-movie")
include(":graphql-dgs:service-director")

include(":graphql-spqr:service-movie")
include(":graphql-spqr:service-director")
include(":graphql-spqr:graphql-spqr-federation")