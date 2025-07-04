package com.recordsapi.graphql;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import lombok.extern.slf4j.Slf4j;


@DgsComponent
@Slf4j
public class HelloDataFetcher {

    @DgsQuery
    public String hello() {
        log.info("Hello query executed");
        return "Hello, Intern!";
    }
}
