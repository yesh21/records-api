package com.recordsapi.graphql;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class HelloDataFetcherIntegrationTest {

    @Autowired
    DgsQueryExecutor dgsQueryExecutor;

    @Test
    void helloQuery_ReturnsGreeting() {
        String result = dgsQueryExecutor.executeAndExtractJsonPath(
                "{ hello }", "data.hello"
        );
        assertThat(result).isEqualTo("Hello, Intern!");
    }
}

