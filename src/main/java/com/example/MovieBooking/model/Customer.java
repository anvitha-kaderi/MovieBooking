package com.example.MovieBooking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uid")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name="email_id")
    private String emailid;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL , fetch = FetchType.LAZY , orphanRemoval=true)
    @JsonBackReference
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<Booking> bookings;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL , fetch = FetchType.LAZY, orphanRemoval=true)
    @JsonBackReference
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<Payment> payments;







    @JsonBackReference
    @JsonProperty
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Set<Booking> getBookings() {
        return bookings;
    }

    @JsonBackReference
    @JsonProperty
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Set<Payment> getPayments() {
        return payments;
    }

    @JsonBackReference
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }


    @JsonBackReference
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }
}
