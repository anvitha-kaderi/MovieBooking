package com.example.MovieBooking.Error;

public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(String ms)
    {
        super(ms);
    }
}
