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

    public List<Seat> getSeat() {
        return seatRepo.findAll();
    }

    public void addSeat(int id,Seat seat) {
       Optional<Hall>hall= hallRepo.findById(id);

        if(hall.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Data already exists");
        }
        seat.setHall(hall.get());
        seatRepo.save(seat);
    }


    public void updateSeat(int id, Seat seat) {
        Optional<Seat> c= Optional.ofNullable(seatRepo.findById(id).orElseThrow(() -> new SeatNotFoundException(Integer.toString(id))));
        if(c.isEmpty())
        {
            throw new SeatNotFoundException(" Seat Not found with id "+id);
        }
        Seat newseat=c.get();
        newseat.setNo(seat.getNo());
        newseat.setHall(seat.getHall());
        seatRepo.save(newseat);
    }


    public void deleteSeat(int id) {
        Optional<Seat> seat=seatRepo.findById(id);
        if(seat.isEmpty())
        {
            throw new SeatNotFoundException(" Seat Not found with id "+id);
        }
        seatRepo.deleteById(id);
    }
}
