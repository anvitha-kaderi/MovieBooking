package com.example.MovieBooking.Repository;

import com.example.MovieBooking.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByNameAndEmailid(String name, String emailid);
}
