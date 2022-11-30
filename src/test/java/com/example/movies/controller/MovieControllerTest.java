package com.example.movies.controller;

import com.example.movies.domain.Movie;
import com.example.movies.domain.MovieDto;
import com.example.movies.mapper.MovieMapper;
import com.example.movies.service.DbService;
import com.example.movies.service.MovieService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @MockBean
    private DbService dbService;

    @MockBean
    private MovieMapper movieMapper;

    Movie movie = new Movie(1L, "test title", "test plot", "test genre", "test director", "test poster");
    MovieDto movieDto = new MovieDto(1L, "test title", "test plot", "test genre", "test director", "test poster");

    @Test
    void shouldFetchMovie() throws Exception {
        //Given
        when(movieService.fetchMovie("test","test")).thenReturn(movieDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/movies")
                        .queryParam("title", "test")
                        .queryParam("year", "test")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.Title", Matchers.is("test title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.Plot", Matchers.is("test plot")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.Genre", Matchers.is("test genre")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.Director", Matchers.is("test director")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.Poster", Matchers.is("test poster")));
    }

    @Test
    void shouldAddMovieToFavorites() throws Exception {
        //Given
        when((movieMapper.mapToMovie(any()))).thenReturn(movie);
        when((movieMapper.mapToMovieDto(dbService.saveMovie(movie)))).thenReturn(movieDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(movieDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/movies")
                        .queryParam("title", "test")
                        .queryParam("year", "test")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
    }

    @Test
    void shouldGetFavoriteMoviesList() throws Exception {
        //Given
        List<MovieDto> movieDtoList = List.of(movieDto);

        when(movieMapper.mapToMovieDtoList(anyList())).thenReturn(movieDtoList);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/movies/favorites"))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].Title", Matchers.is("test title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].Plot", Matchers.is("test plot")));
    }

    @Test
    void shouldGetFavoriteMovieById() throws Exception {
        //Given
        when(dbService.getMovie(1L)).thenReturn(movie);
        when(movieMapper.mapToMovieDto(movie)).thenReturn(movieDto);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/movies/favorites/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.Title", Matchers.is("test title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.Plot", Matchers.is("test plot")));
    }

    @Test
    void shouldDeleteMovieFromFavorites() throws Exception {
        //Given
        when(dbService.getMovie(1L)).thenReturn(movie);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/movies/favorites/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }
}