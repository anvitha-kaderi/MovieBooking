package com.example.MovieBooking.Controller;

import com.example.MovieBooking.Service.HallService;
import com.example.MovieBooking.model.Hall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HallController {

    @Autowired
    public HallService service;
    @GetMapping("/halls")
    public ResponseEntity<List<Hall>> getHalls()
    {
        List<Hall> halls=service.getHall();
        return ResponseEntity.ok().body(halls);
    }

    @PostMapping("/cinema/{id}/hall")
    public ResponseEntity<HttpStatus> saveHall(@PathVariable("id") int id,@RequestBody Hall hall)
    {
        service.addHall(id,hall);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping("/hall/{id}")
    public ResponseEntity<HttpStatus> updateHall(@PathVariable("id") int id , @RequestBody Hall hall)
    {
        service.updateHall(id,hall);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    @DeleteMapping("/hall/{id}")
    public ResponseEntity<HttpStatus> deleteHall(@PathVariable("id") int id)
    {
        service.deleteHall(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
}
