package com.example.MovieBooking.Repository;

import com.example.MovieBooking.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentRepo extends JpaRepository<Payment,Integer> {

}
