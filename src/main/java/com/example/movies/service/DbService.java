package com.example.movies.service;

import com.example.movies.repository.MoviesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DbService {

    private final MoviesRepository moviesRepository;
}
