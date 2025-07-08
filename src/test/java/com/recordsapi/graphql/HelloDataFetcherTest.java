package com.recordsapi.graphql;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class HelloDataFetcherTest {

    @Test
    void getHello_ShouldReturnGreeting() {
        HelloDataFetcher fetcher = new HelloDataFetcher();
        String result = fetcher.hello();
        assertThat(result).isEqualTo("Hello, Intern!");
    }
}

