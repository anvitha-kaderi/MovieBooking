package com.example.MovieBooking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bid")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_sid")
    @JsonManagedReference
    @JsonIgnore
    private Show show;

    @JsonManagedReference
    @JsonIgnore
    public Show getShow() {
        return show;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_cid")
    @JsonManagedReference
    @JsonIgnore
    private Customer customer;

    @JsonManagedReference
    @JsonIgnore
    public Customer getCustomer() {
        return customer;
    }
    @JsonFormat(pattern="HH:mm:ss")
    private String bookingTime;


    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonBackReference
    @JsonIgnore
    private Set<SeatBooked> bookedSeats;

    @JsonBackReference
    @JsonIgnore
    public Set<SeatBooked> getBookedSeats() {
        return bookedSeats;
    }
}
