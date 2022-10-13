package com.example.MovieBooking.model;


import com.fasterxml.jackson.annotation.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="pid")
    private int id;

    @JsonFormat(pattern="HH:mm:ss")
    private String paymentTime;

    @ManyToOne
    @JoinColumn(name = "customer_uid")
    @JsonIgnore
    @JsonManagedReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Customer customer;

    @JsonProperty
    @JsonManagedReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Customer getCustomer() {
        return customer;
    }
    @JsonIgnore
    @JsonManagedReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
