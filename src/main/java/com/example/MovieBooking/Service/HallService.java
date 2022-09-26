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

    public List<Hall> get_Hall() {
        return hallRepo.findAll();
    }

    public void add_Hall(int id,Hall cine) {
        Optional<Hall> c=hallRepo.findByHallnoAndSize(cine.getHallno(), cine.getSize());
        Optional<Cinema>cy= cinemaRepo.findById(id);
        if(c.isPresent() || cy.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Data already exists");
        }
        cine.setCinema(cy.get());
        hallRepo.save(cine);
    }


    public void updating_Hall(int id, Hall cust) {
        Optional<Hall> c= Optional.ofNullable(hallRepo.findById(id).orElseThrow(() -> new HallNotFoundException(Integer.toString(id))));
        if(c.isEmpty())
        {
            throw new HallNotFoundException(" Hall Not found with id "+id);
        }
        Hall custo=c.get();
        custo.setHallno(cust.getHallno());
        custo.setSize(cust.getSize());
        hallRepo.save(custo);
    }


    public void deleleting_Hall(int id) {
        Optional<Hall> c=hallRepo.findById(id);
        if(c.isEmpty())
        {
            throw new HallNotFoundException(" Hall Not found with id "+id);
        }
        hallRepo.deleteById(id);
    }
}
