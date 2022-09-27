package com.example.MovieBooking.Service;

import com.example.MovieBooking.Error.CinemaNotFoundException;
import com.example.MovieBooking.Error.CityNotFoundException;
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
    public List<Cinema> getCinema() {
        return cinemaRepo.findAll();
    }

    public void addCinema(int id, Cinema cinema) {
        Optional<City>city= cityRepo.findById(id);
        if(city.isEmpty())
        {
            throw new CityNotFoundException("city not found ");
        }
        cinema.setCity(city.get());
        cinemaRepo.save(cinema);
    }


    public void updateCinema(int id, Cinema cinema) {
        Optional<Cinema> cinema1= Optional.ofNullable(cinemaRepo.findById(id).orElseThrow(() -> new CinemaNotFoundException(Integer.toString(id))));
       if(cinema1.isEmpty())
        {
            throw new CinemaNotFoundException(" Cinema Not found with id "+id);
        }
        Cinema updatedcinema=cinema1.get();
        updatedcinema.setName(cinema.getName());
        cinemaRepo.save(updatedcinema);
    }


    public void deleteCinema(int id) {
        Optional<Cinema> cinema=cinemaRepo.findById(id);
        if(cinema.isEmpty())
        {
            throw new CinemaNotFoundException(" Cinema Not found with id "+id);
        }
        cinemaRepo.deleteById(id);
    }
}
