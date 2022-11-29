package com.example.movies.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "favorite_movies")
public class Movie {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    private String plot;

    @Column
    private String genre;

    @Column
    private String director;

    @Column
    private String poster;
}
