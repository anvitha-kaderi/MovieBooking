package com.example.MovieBooking.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class Payment{
    @Id
    @GeneratedValue(generator= "uuid2")
    @Column(name="pid")
    private UUID id;

    private String paymentTime;

    @ManyToOne
    @JoinColumn(name = "customer_uid")
    @JsonIgnore
    @JsonManagedReference
    private Customer customer;

    @JsonIgnore
    @JsonManagedReference
    public Customer getCustomer() {
        return customer;
    }
}
