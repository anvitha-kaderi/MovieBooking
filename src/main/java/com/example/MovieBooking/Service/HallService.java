package com.example.MovieBooking.Service;

import com.example.MovieBooking.Error.HallNotFoundException;
import com.example.MovieBooking.Repository.CinemaRepo;
import com.example.MovieBooking.Repository.HallRepo;
import com.example.MovieBooking.Repository.CityRepo;
import com.example.MovieBooking.model.Cinema;
import com.example.MovieBooking.model.Hall;
import com.example.MovieBooking.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class HallService {
    @Autowired
    public HallRepo hallRepo;
    @Autowired
    public CinemaRepo cinemaRepo;

    public List<Hall> getHall() {
        return hallRepo.findAll();
    }

    public void addHall(int id,Hall hall) {
        Optional<Hall> hall1=hallRepo.findByHallnoAndSize(hall.getHallno(), hall.getSize());
        Optional<Cinema>cinema= cinemaRepo.findById(id);
        if(hall1.isPresent() || cinema.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Data already exists");
        }
        hall.setCinema(cinema.get());
        hallRepo.save(hall);
    }


    public void updateHall(int id, Hall hall) {
        Optional<Hall> c= Optional.ofNullable(hallRepo.findById(id).orElseThrow(() -> new HallNotFoundException(Integer.toString(id))));
        if(c.isEmpty())
        {
            throw new HallNotFoundException(" Hall Not found with id "+id);
        }
        Hall newhall=c.get();
        newhall.setHallno(hall.getHallno());
        newhall.setSize(hall.getSize());
        hallRepo.save(newhall);
    }


    public void deleteHall(int id) {
        Optional<Hall> hall=hallRepo.findById(id);
        if(hall.isEmpty())
        {
            throw new HallNotFoundException(" Hall Not found with id "+id);
        }
        hallRepo.deleteById(id);
    }
}
