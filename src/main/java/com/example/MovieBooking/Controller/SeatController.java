package com.example.MovieBooking.Controller;

import com.example.MovieBooking.Service.SeatService;
import com.example.MovieBooking.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SeatController {

    @Autowired
    public SeatService service;
    @GetMapping("/seats")
    public ResponseEntity<List<Seat>> getSeats()
    {
        List<Seat> seats=service.getSeat();
        return ResponseEntity.ok().body(seats);
    }

    @PostMapping("/hall/{id}/seat")
    public ResponseEntity<Seat> saveSeats(@PathVariable("id") int id,@RequestBody Seat seat)
    {
        Seat newseat=service.addSeat(id,seat);
        return ResponseEntity.status(HttpStatus.CREATED).body(newseat);
    }

    @PutMapping("/seat/{id}")
    public ResponseEntity<Seat> updateSeat(@PathVariable("id") int id , @RequestBody Seat seat)
    {
        Seat updatedseat=service.updateSeat(id,seat);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedseat);
    }

    @DeleteMapping("/seat/{id}")
    public ResponseEntity<HttpStatus> clearSeat(@PathVariable("id") int id)
    {
        service.deleteSeat(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
}
