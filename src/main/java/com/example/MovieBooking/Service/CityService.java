package com.example.MovieBooking.Service;

import com.example.MovieBooking.Error.CityNotFoundException;
import com.example.MovieBooking.Repository.CityRepo;
import com.example.MovieBooking.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    @Autowired
    public CityRepo cityRepo;
    public List<City> get_City() {
        return cityRepo.findAll();
    }

    public void add_City(City city) {
        Optional<City> c=cityRepo.findByCitynameAndState(city.getCityname(),city.getState());
        if(c.isPresent())
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Data already exists");
        }
        cityRepo.save(city);
    }


    public void updating_City(int id, City city) {
        Optional<City> c= Optional.ofNullable(cityRepo.findById(id).orElseThrow(() -> new CityNotFoundException(Integer.toString(id))));
        if(c.isEmpty())
        {
            throw new CityNotFoundException(" City Not found with id "+id);
        }
        City cityo=c.get();
        cityo.setState(city.getState());
        cityo.setCityname(city.getCityname());
        cityRepo.save(cityo);
    }


    public void deleleting_City(int id) {
        Optional<City> c=cityRepo.findById(id);
        if(c.isEmpty())
        {
            throw new CityNotFoundException(" City Not found with id "+id);
        }
        cityRepo.deleteById(id);
    }
}
