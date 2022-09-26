package com.example.MovieBooking.Repository;

import com.example.MovieBooking.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepo extends JpaRepository<City,Integer> {
    Optional<City> findByCitynameAndState(String cityname, String state);
}
