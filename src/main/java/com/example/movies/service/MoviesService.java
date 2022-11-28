package com.example.movies.service;

import com.example.movies.client.MoviesClient;
import com.example.movies.domain.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MoviesService {
    private final MoviesClient moviesClient;

    public MovieDto fetchMovie(String title) {
        return moviesClient.getMovie(title);
    }
}