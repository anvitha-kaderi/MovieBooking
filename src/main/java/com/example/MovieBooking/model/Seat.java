package com.example.MovieBooking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="stid")
    private int id;

    private int no;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hall_hid")
    @JsonManagedReference
    @JsonIgnore
    private Hall hall;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonBackReference
    @JsonIgnore
    private Set<SeatBooked> bookedSeats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hall_hid")
    @JsonManagedReference
    @JsonIgnore
    public Hall getHall() {
        return hall;
    }

    @JsonBackReference
    @JsonIgnore
    public Set<SeatBooked> getBookedSeats() {
        return bookedSeats;
    }
}
