package com.example.MovieBooking.Service;

import com.example.MovieBooking.Error.SeatBookedNotFoundException;
import com.example.MovieBooking.Repository.*;
import com.example.MovieBooking.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public List<SeatBooked> get_SeatBooked() {
        return seatBookedRepo.findAll();
    }

    public void add_SeatBooked(int id1,int id2,int id3,SeatBooked cine) {
        Optional<Seat> c=seatRepo.findById(id1);
        Optional<Booking> cb=bookingRepo.findById(id2);
        Optional<Show>cs= showRepo.findById(id3);
        if(c.isEmpty() || cb.isEmpty() || cs.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Enter correct data");
        }
        cine.setSeat(c.get());
        cine.setBooking(cb.get());
        cine.setShow(cs.get());
        seatBookedRepo.save(cine);
    }





    public void deleleting_SeatBooked(int id) {
        Optional<SeatBooked> c=seatBookedRepo.findById(id);
        if(c.isEmpty())
        {
            throw new SeatBookedNotFoundException(" SeatBooked Not found with id "+id);
        }
        seatBookedRepo.deleteById(id);
    }
}
