package com.example.MovieBooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seatbooked")
public class SeatBooked {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_stid")
    @JsonManagedReference
    @JsonIgnore
    private Seat seat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_bid")
    @JsonManagedReference
    @JsonIgnore
    private Booking booking;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_sid")
    @JsonManagedReference
    @JsonIgnore
    private Show show;


    @JsonManagedReference
    @JsonIgnore
    public Seat getSeat() {
        return seat;
    }


    @JsonManagedReference
    @JsonIgnore
    public Show getShow() {
        return show;
    }

    @JsonManagedReference
    @JsonIgnore
    public Booking getBooking() {
        return booking;
    }

}
