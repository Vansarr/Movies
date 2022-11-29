package com.example.movies.service;

import com.example.movies.client.MovieClient;
import com.example.movies.controller.MovieNotFoundException;
import com.example.movies.domain.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieClient movieClient;

    public MovieDto fetchMovie(String title) {
        return movieClient.getMovie(title);
    }
}