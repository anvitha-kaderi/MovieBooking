package com.example.MovieBooking.Controller;

import com.example.MovieBooking.Service.CustService;
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
    @GetMapping("/cinema/{id}/halls")
    public ResponseEntity<List<Hall>> halllist(@PathVariable("id") int id)
    {
        List<Hall> c=service.get_Hall();
        return ResponseEntity.ok().body(c);
    }

    @PostMapping("/cinema/{id}/hall")
    public ResponseEntity<HttpStatus> Hallsposting(@PathVariable("id") int id,@RequestBody Hall cust)
    {
        service.add_Hall(id,cust);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping("/hall/{id}")
    public ResponseEntity<HttpStatus> updatinghall(@PathVariable("id") int id , @RequestBody Hall cust)
    {
        service.updating_Hall(id,cust);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    @DeleteMapping("/hall/{id}")
    public ResponseEntity<HttpStatus> deletinghall(@PathVariable("id") int id)
    {
        service.deleleting_Hall(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
}
