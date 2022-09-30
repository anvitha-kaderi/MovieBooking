package com.example.MovieBooking.Service;


import com.example.MovieBooking.Repository.PaymentRepo;
import com.example.MovieBooking.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    public PaymentRepo paymentRepo;
    public List<Payment> getpayment()
    {
        return paymentRepo.findAll();
    }
}
