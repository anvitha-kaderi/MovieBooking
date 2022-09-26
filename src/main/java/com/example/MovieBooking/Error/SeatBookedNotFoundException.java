package com.example.MovieBooking.Error;

public class SeatBookedNotFoundException extends RuntimeException{
    public SeatBookedNotFoundException(String ms)
    {
        super(ms);
    }
}
