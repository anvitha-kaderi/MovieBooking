package com.example.MovieBooking.Controller;

import com.example.MovieBooking.Service.CityService;
import com.example.MovieBooking.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController {

    @Autowired
    public CityService service;
    @GetMapping("/cities")
    public ResponseEntity<List<City>> custlist()
    {
        List<City> c=service.get_City();
        return ResponseEntity.ok().body(c);
    }

    @PostMapping("/city")
    public ResponseEntity<HttpStatus> postingCity(@RequestBody City cust)
    {
        service.add_City(cust);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping("/city/{id}")
    public ResponseEntity<HttpStatus> updatingcity(@PathVariable("id") int id , @RequestBody City cust)
    {
        service.updating_City(id,cust);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    @DeleteMapping("/city/{id}")
    public ResponseEntity<HttpStatus> deletingcity(@PathVariable("id") int id)
    {
        service.deleleting_City(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
}
