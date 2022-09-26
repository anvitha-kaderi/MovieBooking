package com.example.MovieBooking.Error;

public class CityNotFoundException extends RuntimeException{
    public CityNotFoundException(String ms)
    {
        super(ms);
    }
}
