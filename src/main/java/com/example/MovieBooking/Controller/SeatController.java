package com.example.MovieBooking.Controller;

import com.example.MovieBooking.Service.CustService;
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
    public ResponseEntity<List<Seat>> Seatlist()
    {
        List<Seat> c=service.get_Seat();
        return ResponseEntity.ok().body(c);
    }

    @PostMapping("/hall/{id}/seat")
    public ResponseEntity<HttpStatus> Seatsposting(@PathVariable("id") int id,@RequestBody Seat cust)
    {
        service.add_Seat(id,cust);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping("/seat/{id}")
    public ResponseEntity<HttpStatus> updatingSeat(@PathVariable("id") int id , @RequestBody Seat cust)
    {
        service.updating_Seat(id,cust);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    @DeleteMapping("/seat/{id}")
    public ResponseEntity<HttpStatus> deletingSeat(@PathVariable("id") int id)
    {
        service.deleleting_Seat(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
}
