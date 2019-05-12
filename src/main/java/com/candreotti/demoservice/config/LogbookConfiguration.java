package com.candreotti.demoservice.config;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.function.BinaryOperator;

import static org.zalando.logbook.HeaderFilters.authorization;
import static org.zalando.logbook.QueryFilters.accessToken;
import static org.zalando.logbook.QueryFilters.replaceQuery;
import static org.zalando.logbook.HeaderFilters.eachHeader;

@Configuration
public class LogbookConfiguration {
    @Bean
    public Logbook logbook() {
        BinaryOperator<String> filterSecretHeader = (s1, s2)-> s1.toLowerCase().equals("x-secret") ? "<secret>" : s2;

        return Logbook.builder()
                .queryFilter(accessToken())
                .queryFilter(replaceQuery("password", "<secret>"))
                .headerFilter(authorization())
                .headerFilter(eachHeader(filterSecretHeader))
                .bodyFilter(
                        BodyFilters.replaceJsonStringProperty(
                                new HashSet<>(Arrays.asList("password")), "<secret>"))
                .formatter(new JsonHttpLogFormatter())
                .writer(new DefaultHttpLogWriter(
                        LoggerFactory.getLogger(LogbookConfiguration.class),
                        DefaultHttpLogWriter.Level.INFO))
                .build();
    }
}
