package com.example.movies.service;

import com.example.movies.controller.MovieNotFoundException;
import com.example.movies.domain.Movie;
import com.example.movies.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbService {

    private final MovieRepository repository;

    public List<Movie> getAllMovies() {
        return repository.findAll();
    }

    public Movie getMovie(final Long id) throws MovieNotFoundException {
        return repository.findById(id).orElseThrow(MovieNotFoundException::new);
    }

    public Movie saveMovie(final Movie movie){
        return repository.save(movie);
    }

    public void deleteMovie(Long id) {
        repository.deleteById(id);
    }
}
