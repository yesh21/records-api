package com.recordsapi.graphql;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class HelloDataFetcherTest {

    @Test
    void hello_ReturnsGreeting() {
        // Arrange
        HelloDataFetcher fetcher = new HelloDataFetcher();

        // Act
        String result = fetcher.hello();

        // Assert
        assertThat(result).isEqualTo("Hello, Intern!");
    }
}

