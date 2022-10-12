package com.example.MovieBooking.Service;

import com.example.MovieBooking.Error.BookingNotFoundException;
import com.example.MovieBooking.Repository.*;
import com.example.MovieBooking.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service

public class BookingService {
    @Autowired
    public BookingRepo bookingRepo;
    @Autowired
    public CustomerRepo customerRepo;
    @Autowired
    public ShowRepo showRepo;

    @Autowired PaymentRepo paymentRepo;

    public List<Booking> getBooking() {
        return bookingRepo.findAll();
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking addBooking(int id1,int id2,Booking booking) {
        Optional<Customer> customer= customerRepo.findById(id1);
        Optional<Show> show=showRepo.findById(id2);
        if(customer.isEmpty() || show.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT," Error ");
        }
        Payment payment=new Payment();
        payment.setCustomer(customer.get());
        payment.setPaymentTime(String.valueOf(LocalTime.now()));
        booking.setCustomer(customer.get());
        booking.setShow(show.get());
        paymentRepo.save(payment);
        bookingRepo.save(booking);
        return booking;
    }



    public void deleteBooking(int id) {
        Optional<Booking> booking=bookingRepo.findById(id);
        if(booking.isEmpty())
        {
            throw new BookingNotFoundException(" Booking Not found with id "+id);
        }
        bookingRepo.deleteById(id);
    }
}
