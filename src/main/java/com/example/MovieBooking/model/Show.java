package com.example.MovieBooking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "show")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="sid")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="movie_mid")
    @JsonManagedReference
    @JsonIgnore
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="hall_hid")
    @JsonManagedReference
    @JsonIgnore
    private Hall hall;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private String date;

    @JsonFormat(pattern="HH:mm:ss")
    private String start_time;

    @JsonFormat(pattern="HH:mm:ss")
    private String end_time;

    private int cost;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonBackReference
    @JsonIgnore
    private Set<SeatBooked> bookedSeats;


    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    @JsonIgnore
    private Set<Booking> bookings;


    @JsonBackReference
    @JsonIgnore
    public Set<Booking> getBookings() {
        return bookings;
    }

    @JsonBackReference
    @JsonIgnore
    public Set<SeatBooked> getBookedSeats() {
        return bookedSeats;
    }

    @JsonManagedReference
    @JsonIgnore
    public Movie getMovie() {
        return movie;
    }

    @JsonManagedReference
    @JsonIgnore
    public Hall getHall() {
        return hall;
    }
}
