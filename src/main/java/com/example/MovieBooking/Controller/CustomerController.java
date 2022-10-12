package com.example.MovieBooking.Controller;

import com.example.MovieBooking.Service.CustomerService;
import com.example.MovieBooking.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    public CustomerService service;
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCusotmers()
    {
        List<Customer> customers=service.getCustomer();
        return ResponseEntity.ok().body(customers);
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer)
    {
        Customer newcustomer=service.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(newcustomer);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id , @RequestBody Customer customer)
    {
        Customer updatedcustomer=service.updateCustomer(id,customer);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedcustomer);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<HttpStatus> clearCustomer(@PathVariable("id") int id)
    {
        service.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
    }
