package com.example.MovieBooking.Controller;

import com.example.MovieBooking.Service.PaymentService;
import com.example.MovieBooking.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentController {


    @Autowired
    public PaymentService paymentService;


    @GetMapping("/payment")
    public ResponseEntity<List<Payment>> getpayments()
    {
        List<Payment> payments=paymentService.getpayment();
        return ResponseEntity.ok().body(payments);
    }

//    @DeleteMapping("/payment/{id}")
//    public ResponseEntity<HttpStatus> clearpayment(@PathVariable("id") int id)
//    {
//        paymentService.deletepayment();
//        return ResponseEntity.ok().body(null);
//    }
}
