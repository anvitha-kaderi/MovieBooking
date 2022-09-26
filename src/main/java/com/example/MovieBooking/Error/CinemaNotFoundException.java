package com.example.MovieBooking.Error;

public class CinemaNotFoundException extends RuntimeException{
    public CinemaNotFoundException(String ms)
    {
        super(ms);
    }
}
