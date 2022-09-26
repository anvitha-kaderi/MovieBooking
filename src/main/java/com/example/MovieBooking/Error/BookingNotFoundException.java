package com.example.MovieBooking.Error;

public class BookingNotFoundException extends RuntimeException{
    public BookingNotFoundException(String ms)
    {
        super(ms);
    }
}
