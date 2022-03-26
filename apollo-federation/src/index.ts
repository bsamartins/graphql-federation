import {ApolloGateway, IntrospectAndCompose} from "@apollo/gateway";
import {ApolloServer} from "apollo-server";

const gateway = new ApolloGateway({
    supergraphSdl: new IntrospectAndCompose({
        subgraphs: [
            { name: 'movies', url: "http://localhost:8081" }
        ]
    })
});

const server = new ApolloServer({
    gateway: gateway,
});

server.listen().then(({ url }) => {
    console.log(`🚀 Server ready at ${url}`);
});