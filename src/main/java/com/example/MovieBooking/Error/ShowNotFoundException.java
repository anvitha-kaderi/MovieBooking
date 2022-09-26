package com.example.MovieBooking.Error;

public class ShowNotFoundException extends RuntimeException{
    public ShowNotFoundException(String ms)
    {
        super(ms);
    }
}
