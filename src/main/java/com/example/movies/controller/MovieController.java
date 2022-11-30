package com.example.movies.controller;

import com.example.movies.domain.Movie;
import com.example.movies.domain.MovieDto;
import com.example.movies.mapper.MovieMapper;
import com.example.movies.service.DbService;
import com.example.movies.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/movies")
public class MovieController {

    private final MovieService movieService;
    private final DbService dbService;
    private final MovieMapper movieMapper;

    @GetMapping()
    public ResponseEntity<MovieDto> getMovieByTitleAndYear(@RequestParam String title, @RequestParam(required = false) String year) {
        return ResponseEntity.ok(movieService.fetchMovie(title, year));
    }

    @PostMapping
    public ResponseEntity<Void> addMovieToFavorites(@RequestParam String title, @RequestParam String year) throws MovieNotFoundException {
        Movie movie = movieMapper.mapToMovie(movieService.fetchMovie(title, year));
        if(movie.getTitle() == null) throw new MovieNotFoundException();
        dbService.saveMovie(movie);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<MovieDto>> getAllFavoriteMovies() {
        return ResponseEntity.ok(movieMapper.mapToMovieDtoList(dbService.getAllMovies()));
    }

    @GetMapping("/favorites/{movieId}")
    public ResponseEntity<MovieDto> getFavoriteMovieById(@PathVariable Long movieId) throws MovieNotFoundException {
        return ResponseEntity.ok(movieMapper.mapToMovieDto(dbService.getMovie(movieId)));
    }

    @DeleteMapping("/favorites/{movieId}")
    public ResponseEntity<Void> deleteFavoriteMovie(@PathVariable Long movieId) {
        dbService.deleteMovie(movieId);
        return ResponseEntity.ok().build();
    }
}
