package com.example.MovieBooking.model;

import com.fasterxml.jackson.annotation.*;
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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Hall hall;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL,fetch = FetchType.LAZY , orphanRemoval=true)
    @JsonBackReference
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<SeatBooked> bookedSeats;



   // @ManyToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name = "hall_hid")
    @JsonManagedReference
    @JsonProperty
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Hall getHall() {
        return hall;
    }

    @JsonBackReference
    @JsonProperty
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Set<SeatBooked> getBookedSeats() {
        return bookedSeats;
    }

    @JsonBackReference
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public void setBookedSeats(Set<SeatBooked> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    @JsonManagedReference
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public void setHall(Hall hall) {
        this.hall = hall;
    }
}
