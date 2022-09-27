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
    public ResponseEntity<List<City>> getCities()
    {
        List<City> city=service.getCity();
        return ResponseEntity.ok().body(city);
    }

    @PostMapping("/city")
    public ResponseEntity<HttpStatus> saveCity(@RequestBody City city)
    {
        service.addCity(city);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping("/city/{id}")
    public ResponseEntity<HttpStatus> updateCity(@PathVariable("id") int id , @RequestBody City city)
    {
        service.updateCity(id,city);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    @DeleteMapping("/city/{id}")
    public ResponseEntity<HttpStatus> clearCity(@PathVariable("id") int id)
    {
        service.deleteCity(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
}
