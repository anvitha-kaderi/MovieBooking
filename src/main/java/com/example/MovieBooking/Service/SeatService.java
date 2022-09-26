package com.example.MovieBooking.Service;

import com.example.MovieBooking.Error.SeatNotFoundException;
import com.example.MovieBooking.Repository.HallRepo;
import com.example.MovieBooking.Repository.SeatRepo;
import com.example.MovieBooking.model.Hall;
import com.example.MovieBooking.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService {
    @Autowired
    public SeatRepo seatRepo;
    @Autowired
    public HallRepo hallRepo;

    public List<Seat> get_Seat() {
        return seatRepo.findAll();
    }

    public void add_Seat(int id,Seat cine) {
       // Optional<Seat> c=seatRepo.findByNoAndHall(cine.getNo(), cine.getHall());
       Optional<Hall>cy= hallRepo.findById(id);

        if(cy.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Data already exists");
        }
        cine.setHall(cy.get());
        seatRepo.save(cine);
    }


    public void updating_Seat(int id, Seat cust) {
        Optional<Seat> c= Optional.ofNullable(seatRepo.findById(id).orElseThrow(() -> new SeatNotFoundException(Integer.toString(id))));
        if(c.isEmpty())
        {
            throw new SeatNotFoundException(" Seat Not found with id "+id);
        }
        Seat custo=c.get();
        custo.setNo(cust.getNo());
        custo.setHall(cust.getHall());
        seatRepo.save(custo);
    }


    public void deleleting_Seat(int id) {
        Optional<Seat> c=seatRepo.findById(id);
        if(c.isEmpty())
        {
            throw new SeatNotFoundException(" Seat Not found with id "+id);
        }
        seatRepo.deleteById(id);
    }
}
