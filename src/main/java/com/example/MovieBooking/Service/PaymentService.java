package com.example.MovieBooking.Service;


import com.example.MovieBooking.Error.SeatNotFoundException;
import com.example.MovieBooking.Repository.PaymentRepo;
import com.example.MovieBooking.model.Payment;
import com.example.MovieBooking.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    public PaymentRepo paymentRepo;
    public List<Payment> getpayment()
    {
        return paymentRepo.findAll();
    }

    public void deletepayment(int id)
    {
        Optional<Payment> payment=paymentRepo.findById(id);
        if(payment.isEmpty())
        {
            throw new SeatNotFoundException(" Seat Not found with id "+id);
        }
        paymentRepo.deleteById(id);
    }
}
