package com.example.MovieBooking.Repository;

import com.example.MovieBooking.model.Hall;
import com.example.MovieBooking.model.Movie;
import com.example.MovieBooking.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface ShowRepo extends JpaRepository<Show, Integer> {

    //Optional<Show> findByDateAndStart_timeAndEnd_time(LocalDate date, LocalTime start_time, LocalDate end_time);
}
