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
    public List<City> getCity() {
        return cityRepo.findAll();
    }

    public City addCity(City city) {
        Optional<City> city1=cityRepo.findByCitynameAndState(city.getCityname(),city.getState());
        if(city1.isPresent())
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Data already exists");
        }
        cityRepo.save(city);
        return  city;
    }


    public City updateCity(int id, City city) {
        Optional<City> city1= Optional.ofNullable(cityRepo.findById(id).orElseThrow(() -> new CityNotFoundException(Integer.toString(id))));
        if(city1.isEmpty())
        {
            throw new CityNotFoundException(" City Not found with id "+id);
        }
        City newcity=city1.get();
        newcity.setState(city.getState());
        newcity.setCityname(city.getCityname());
        cityRepo.save(newcity);
        return newcity;
    }


    public void deleteCity(int id) {
        Optional<City> city=cityRepo.findById(id);
        if(city.isEmpty())
        {
            throw new CityNotFoundException(" City Not found with id "+id);
        }
        cityRepo.delete(id);
    }
}
