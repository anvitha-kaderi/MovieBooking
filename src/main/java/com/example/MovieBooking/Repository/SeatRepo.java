package com.example.MovieBooking.Repository;

import com.example.MovieBooking.model.Hall;
import com.example.MovieBooking.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepo extends JpaRepository<Seat, Integer> {
    Optional<Seat> findByNoAndHall(int no, Hall hall);
}
