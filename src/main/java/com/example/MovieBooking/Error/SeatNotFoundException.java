package com.example.MovieBooking.Error;

public class SeatNotFoundException extends RuntimeException{
    public SeatNotFoundException(String ms)
    {
        super(ms);
    }
}
