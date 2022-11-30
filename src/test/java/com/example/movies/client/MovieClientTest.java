package com.example.movies.client;

import com.example.movies.configuration.MovieConfig;
import com.example.movies.domain.MovieDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieClientTest {

    @InjectMocks
    private MovieClient movieClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private MovieConfig movieConfig;

    @Test
    public void shouldFetchMovie() throws URISyntaxException {
        //Given
        when(movieConfig.getOmdbApiEndpoint()).thenReturn("http://test.com/");
        when(movieConfig.getOmdbAppKey()).thenReturn("test_key");

        MovieDto movie = new MovieDto(
                1L,
                "test_title",
                "test_plot",
                "test_genre",
                "test_director",
                "test_poster");

        URI uri = new URI("http://test.com/?t=test_title&y&apikey=test_key");

        when(restTemplate.getForObject(uri, MovieDto.class)).thenReturn(movie);

        //When
        MovieDto fetchedMovie = movieClient.getMovie("test_title", null);

        //Then
        assertEquals("test_title", fetchedMovie.getTitle());
        assertEquals("test_plot", fetchedMovie.getPlot());
        assertEquals("test_genre", fetchedMovie.getGenre());
        assertEquals("test_director", fetchedMovie.getDirector());
        assertEquals("test_poster", fetchedMovie.getPoster());
    }
}