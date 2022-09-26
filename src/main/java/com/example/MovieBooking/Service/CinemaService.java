package com.example.MovieBooking.Service;

import com.example.MovieBooking.Error.CinemaNotFoundException;
import com.example.MovieBooking.Repository.CinemaRepo;
import com.example.MovieBooking.Repository.CityRepo;
import com.example.MovieBooking.model.Cinema;
import com.example.MovieBooking.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaService {
    @Autowired
    public CinemaRepo cinemaRepo;

    @Autowired
    public CityRepo cityRepo;
    public List<Cinema> get_Cinema() {
        return cinemaRepo.findAll();
    }

    public void add_Cinema(int id, Cinema cine) {
        //Optional<Cinema> c=cinemaRepo.findByNameAndCity(cine.getName(),cine.getCity());
        Optional<City>cy= cityRepo.findById(id);
        if(cy.isEmpty())
        {
            throw new CinemaNotFoundException("city not found ");
        }
        cine.setCity(cy.get());
        cinemaRepo.save(cine);
    }


    public void updating_Cinema(int id, Cinema cust) {
        Optional<Cinema> c= Optional.ofNullable(cinemaRepo.findById(id).orElseThrow(() -> new CinemaNotFoundException(Integer.toString(id))));
       if(c.isEmpty())
        {
            throw new CinemaNotFoundException(" Cinema Not found with id "+id);
        }
        Cinema custo=c.get();
        custo.setName(cust.getName());
       // custo.setCity(cust.getCity());*/
        cinemaRepo.save(cust);
    }


    public void deleleting_Cinema(int id) {
        Optional<Cinema> c=cinemaRepo.findById(id);
        if(c.isEmpty())
        {
            throw new CinemaNotFoundException(" Cinema Not found with id "+id);
        }
        cinemaRepo.deleteById(id);
    }
}
