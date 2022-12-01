package com.example.movies.controller;

import java.util.function.Supplier;

public class MovieNotFoundException extends Exception{
    public MovieNotFoundException(String message) {
        super(message);
    }
}
