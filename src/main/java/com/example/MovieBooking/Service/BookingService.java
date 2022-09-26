package com.example.MovieBooking.Service;

import com.example.MovieBooking.Error.BookingNotFoundException;
import com.example.MovieBooking.Repository.*;
import com.example.MovieBooking.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public List<Booking> get_Booking() {
        return bookingRepo.findAll();
    }

    public void add_Booking(int id1,int id2,Booking cine) {
        Optional<Customer>cy= customerRepo.findById(id1);
        Optional<Show> c=showRepo.findById(id2);
        if(c.isEmpty() || cy.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT," Error ");
        }
        cine.setCustomer(cy.get());
        cine.setShow(c.get());
        bookingRepo.save(cine);
    }



    public void deleleting_Booking(int id) {
        Optional<Booking> c=bookingRepo.findById(id);
        if(c.isEmpty())
        {
            throw new BookingNotFoundException(" Booking Not found with id "+id);
        }
        bookingRepo.deleteById(id);
    }
}
