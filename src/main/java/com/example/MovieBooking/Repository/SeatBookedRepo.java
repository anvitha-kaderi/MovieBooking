package com.example.MovieBooking.Repository;

import com.example.MovieBooking.model.SeatBooked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatBookedRepo extends JpaRepository<SeatBooked,Integer> {
}
