package com.example.MovieBooking.Service;

import com.example.MovieBooking.Error.SeatBookedNotFoundException;
import com.example.MovieBooking.Repository.*;
import com.example.MovieBooking.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class SeatBookedService {
    @Autowired
    public SeatRepo seatRepo;
    @Autowired
    public BookingRepo bookingRepo;
    @Autowired
    public ShowRepo showRepo;
    @Autowired
    public SeatBookedRepo seatBookedRepo;

    @Autowired
    public PaymentRepo paymentRepo;

    public List<SeatBooked> getSeatBooked() {
        return seatBookedRepo.findAll();
    }

    public boolean checkseat(int seatid)
    {
        Optional<SeatBooked> seat=seatBookedRepo.findById(seatid);
        if(seat.isPresent())
            return false;
        return true;
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public SeatBooked addSeatBooked(int id1,int id2,int id3,SeatBooked seatBooked) {
        Optional<Seat> seat=seatRepo.findById(id1);
        Optional<Booking> booking=bookingRepo.findById(id2);
        Optional<Show>show= showRepo.findById(id3);
        if(seat.isEmpty() || booking.isEmpty() || show.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Enter correct data");
        }
        if(!checkseat(id1))
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,"Seat already booked");
        Payment payment=new Payment();
        Customer customer=booking.get().getCustomer();
        payment.setCustomer(customer);
        payment.setPaymentTime(String.valueOf(LocalTime.now()));
        seatBooked.setSeat(seat.get());
        seatBooked.setBooking(booking.get());
        seatBooked.setShow(show.get());
        paymentRepo.save(payment);
        seatBookedRepo.save(seatBooked);
        return seatBooked;
    }





    public void deleteSeatBooked(int id) {
        Optional<SeatBooked> seatBooked=seatBookedRepo.findById(id);
        if(seatBooked.isEmpty())
        {
            throw new SeatBookedNotFoundException(" SeatBooked Not found with id "+id);
        }
        seatBookedRepo.deleteById(id);
    }
}
