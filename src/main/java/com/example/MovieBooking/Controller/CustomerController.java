package com.example.MovieBooking.Controller;

import com.example.MovieBooking.Service.CustService;
import com.example.MovieBooking.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    public CustService service;
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> custlist()
    {
        List<Customer> c=service.get_customer();
        return ResponseEntity.ok().body(c);
    }

    @PostMapping("/customer")
    public ResponseEntity<HttpStatus> customers(@RequestBody Customer cust)
    {
        service.add_customer(cust);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<HttpStatus> updatingc(@PathVariable("id") int id , @RequestBody Customer cust)
    {
        service.updating_customer(id,cust);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<HttpStatus> deletingc(@PathVariable("id") int id)
    {
        service.deleleting_customer(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
    }
