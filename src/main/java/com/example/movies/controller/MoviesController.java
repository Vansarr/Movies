package com.example.movies.controller;

import com.example.movies.domain.MovieDto;
import com.example.movies.service.MoviesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/movies")
public class MoviesController {

    private final MoviesService moviesService;

    @GetMapping()
    public ResponseEntity<MovieDto> getMovieByTitle(@RequestParam String title) {
        return ResponseEntity.ok(moviesService.fetchMovie(title));
    }

}
