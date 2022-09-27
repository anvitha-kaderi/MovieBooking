package com.example.MovieBooking.Controller;

import com.example.MovieBooking.Service.CinemaService;
import com.example.MovieBooking.model.Cinema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CinemaController {
    @Autowired
    public CinemaService service;
    @GetMapping("/cinemas")
    public ResponseEntity<List<Cinema>> getCinemas()
    {
        List<Cinema> cinema=service.getCinema();
        return ResponseEntity.ok().body(cinema);
    }

    @PostMapping("/city/{id}/cinema")
    public ResponseEntity<HttpStatus> saveCinema(@PathVariable("id") int id, @RequestBody Cinema cinema)
    {
        service.addCinema(id,cinema);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
    @PutMapping("/cinema/{id}")
    public ResponseEntity<HttpStatus> updateCinema(@PathVariable("id") int id , @RequestBody Cinema cinema)
    {
        service.updateCinema(id,cinema);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    @DeleteMapping("/cinema/{id}")
    public ResponseEntity<HttpStatus> clearCinema(@PathVariable("id") int id)
    {
        service.deleteCinema(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
}

