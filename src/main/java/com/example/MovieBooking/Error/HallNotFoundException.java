package com.example.MovieBooking.Error;

public class HallNotFoundException extends RuntimeException{
    public HallNotFoundException(String ms)
    {
        super(ms);
    }
}
