package com.example.MovieBooking.Repository;

import com.example.MovieBooking.model.Cinema;
import com.example.MovieBooking.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CinemaRepo extends JpaRepository<Cinema,Integer> {
   // Optional<Cinema> findByNameAndCity(String name, City city);
}
