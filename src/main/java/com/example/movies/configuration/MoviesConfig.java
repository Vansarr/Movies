package com.example.movies.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Getter
@Configuration
public class MoviesConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Value("${omdb.api.endpoint.prod}")
    private String omdbApiEndpoint;

    @Value("${omdb.app.key}")
    private String omdbAppKey;
}