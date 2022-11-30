package com.example.movies.client;

import com.example.movies.configuration.MovieConfig;
import com.example.movies.domain.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Component
public class MovieClient {

    private final RestTemplate restTemplate;
    private final MovieConfig movieConfig;

    public MovieDto getMovie(String title, String year) {
        URI url = UriComponentsBuilder.fromHttpUrl(movieConfig.getOmdbApiEndpoint())
                .queryParam("t", title)
                .queryParam("y", year)
                .queryParam("apikey", movieConfig.getOmdbAppKey())
                .build()
                .encode()
                .toUri();

        return restTemplate.getForObject(url, MovieDto.class);
    }
}