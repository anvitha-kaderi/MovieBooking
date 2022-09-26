package com.example.MovieBooking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JsonBackReference
    @JsonIgnore
    private Set<Booking> bookings;

    @JsonBackReference
    @JsonIgnore
    public Set<Booking> getBookings() {
        return bookings;
    }
}
