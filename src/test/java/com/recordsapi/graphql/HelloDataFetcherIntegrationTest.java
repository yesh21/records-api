package com.recordsapi.graphql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloDataFetcherIntegrationTest {

    private HttpGraphQlTester graphQlTester;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        WebTestClient client = WebTestClient.bindToServer()
                .baseUrl("http://localhost:" + port + "/graphql")
                .build();
        graphQlTester = HttpGraphQlTester.create(client);
    }

    @Test
    void helloQuery_ReturnsGreeting() {
        // language=GraphQL
        String query = """
            query {
                hello
            }
            """;

        graphQlTester.document(query)
                .execute()
                .path("hello")
                .entity(String.class)
                .satisfies(result -> assertThat(result).isEqualTo("Hello, Intern!"));
    }
}
