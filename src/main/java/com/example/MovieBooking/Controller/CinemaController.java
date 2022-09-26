package com.example.MovieBooking.Controller;

import com.example.MovieBooking.Service.CinemaService;
import com.example.MovieBooking.Service.CustService;
import com.example.MovieBooking.model.Cinema;
import com.example.MovieBooking.model.Customer;
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
    public ResponseEntity<List<Cinema>> cinemalist()
    {
        List<Cinema> c=service.get_Cinema();
        return ResponseEntity.ok().body(c);
    }

    @PostMapping("/city/{id}/cinema")
    public ResponseEntity<HttpStatus> customers(@PathVariable("id") int id, @RequestBody Cinema cust)
    {
        service.add_Cinema(id,cust);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
    @PutMapping("/cinema/{id}")
    public ResponseEntity<HttpStatus> updatingcinema(@PathVariable("id") int id , @RequestBody Cinema cust)
    {
        service.updating_Cinema(id,cust);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    @DeleteMapping("/cinema/{id}")
    public ResponseEntity<HttpStatus> deletingcinema(@PathVariable("id") int id)
    {
        service.deleleting_Cinema(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
}

