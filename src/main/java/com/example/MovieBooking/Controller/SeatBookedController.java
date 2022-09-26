package com.example.MovieBooking.Controller;
import com.example.MovieBooking.Service.SeatBookedService;
import com.example.MovieBooking.model.SeatBooked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SeatBookedController {

    @Autowired
    public SeatBookedService service;
    @GetMapping("/seatsbooked")
    public ResponseEntity<List<SeatBooked>> SeatBookedlist()
    {
        List<SeatBooked> c=service.get_SeatBooked();
        return ResponseEntity.ok().body(c);
    }

    @PostMapping("/seat/{id1}/book/{id2}/show/{id3}/bookseat")
    public ResponseEntity<HttpStatus> SeatBookedsposting(@PathVariable("id1") int id1,@PathVariable("id2") int id2,@PathVariable("id3") int id3,@RequestBody SeatBooked cust)
    {
        service.add_SeatBooked(id1,id2,id3,cust);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }



    @DeleteMapping("/seatbooked/{id}")
    public ResponseEntity<HttpStatus> deletingSeatBooked(@PathVariable("id") int id)
    {
        service.deleleting_SeatBooked(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
}
