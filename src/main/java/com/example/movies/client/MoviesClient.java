package com.example.movies.client;

import com.example.movies.configuration.MoviesConfig;
import com.example.movies.domain.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Component
public class MoviesClient {
    private final MoviesConfig moviesConfig;
    private final RestTemplate restTemplate;

    public MovieDto getMovie(String title) {
        URI url = UriComponentsBuilder.fromHttpUrl(moviesConfig.getOmdbApiEndpoint())
                .queryParam("t", title)
                .queryParam("apikey", moviesConfig.getOmdbAppKey())
                .build()
                .encode()
                .toUri();

        return restTemplate.getForObject(url, MovieDto.class);
    }
}