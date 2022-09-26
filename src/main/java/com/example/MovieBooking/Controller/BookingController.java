package com.example.MovieBooking.Controller;

import com.example.MovieBooking.Service.CustService;
import com.example.MovieBooking.Service.BookingService;
import com.example.MovieBooking.model.Booking;
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
    public ResponseEntity<List<Booking>> Bookinglist()
    {
        List<Booking> c=service.get_Booking();
        return ResponseEntity.ok().body(c);
    }


    //@PostMapping("/customer/{id1}/show/{id2}/booking")
    @PostMapping("/customer/{id1}/book")
    public ResponseEntity<HttpStatus> Bookingsposting(@PathVariable("id1") int id1, @RequestParam("show") int id2,@RequestBody Booking cust)
    {
        service.add_Booking(id1,id2,cust);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }


    @DeleteMapping("/book/{id}")
    public ResponseEntity<HttpStatus> deletingBooking(@PathVariable("id") int id)
    {
        service.deleleting_Booking(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
}
