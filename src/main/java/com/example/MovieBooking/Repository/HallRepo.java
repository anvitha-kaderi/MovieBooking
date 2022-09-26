package com.example.MovieBooking.Repository;

import com.example.MovieBooking.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HallRepo extends JpaRepository<Hall,Integer> {
    Optional<Hall> findByHallnoAndSize(int hallno, int size);
}
