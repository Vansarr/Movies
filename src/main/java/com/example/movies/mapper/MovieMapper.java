package com.example.movies.mapper;

import com.example.movies.domain.Movie;
import com.example.movies.domain.MovieDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieMapper {

    public Movie mapToMovie(final MovieDto movieDto) {
        return new Movie(
                movieDto.getId(),
                movieDto.getTitle(),
                movieDto.getPlot(),
                movieDto.getGenre(),
                movieDto.getDirector(),
                movieDto.getPoster()
        );
    }

    public MovieDto mapToMovieDto(final Movie movie) {
        return new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getPlot(),
                movie.getGenre(),
                movie.getDirector(),
                movie.getPoster()
        );
    }

    public List<MovieDto> mapToMovieDtoList(final List<Movie> movieList) {
        return movieList.stream()
                .map(this::mapToMovieDto)
                .collect(Collectors.toList());
    }
}