package com.example.movies.mapper;

import com.example.movies.domain.Movie;
import com.example.movies.domain.MovieDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieMapperTest {

    @Autowired
    private MovieMapper movieMapper;

    @Test
    void mapToMovieDtoListTest() {
        //Given
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie(1L, "test", "test", "test", "test", "test"));

        //When
        List<MovieDto> mappedList = movieMapper.mapToMovieDtoList(movieList);

        //Then
        assertEquals(mappedList.get(0).getId(), movieList.get(0).getId());
    }

    @Test
    void mapToMovieTest() {
        //Given
        MovieDto movieDto = new MovieDto(1L, "test", "test", "test", "test", "test");

        //When
        Movie mappedMovie = movieMapper.mapToMovie(movieDto);

        //Then
        assertEquals(mappedMovie.getId(), movieDto.getId());
    }

    @Test
    void mapToMovieDtoTest() {
        //Given
        Movie movie = new Movie(1L, "test", "test", "test", "test", "test");

        //When
        MovieDto mappedMovieDto = movieMapper.mapToMovieDto(movie);

        //Then
        assertEquals(mappedMovieDto.getId(), movie.getId());
    }


}