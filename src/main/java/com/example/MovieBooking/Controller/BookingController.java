package com.example.MovieBooking.Controller;

import com.example.MovieBooking.Service.BookingService;
import com.example.MovieBooking.model.Booking;
import com.example.MovieBooking.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {

    @Autowired
    public BookingService service;
    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getBookings()
    {
        List<Booking> bookings=service.getBooking();
        return ResponseEntity.ok().body(bookings);
    }


    @PostMapping("/customer/{id1}/book")
    public ResponseEntity<Booking> saveBooking(@PathVariable("id1") int id1, @RequestParam("show") int id2, @RequestBody Booking booking)
    {
        Booking newbooking= service.addBooking(id1,id2,booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(newbooking);
    }


    @DeleteMapping("/book/{id}")
    public ResponseEntity<HttpStatus> clearBooking(@PathVariable("id") int id)
    {
        service.deleteBooking(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
}
