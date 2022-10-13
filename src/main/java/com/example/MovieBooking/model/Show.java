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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="hall_hid")
    @JsonManagedReference
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Hall hall;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private String date;

    @JsonFormat(pattern="HH:mm:ss")
    private String start_time;

    @JsonFormat(pattern="HH:mm:ss")
    private String end_time;

    private int cost;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL,fetch = FetchType.LAZY , orphanRemoval=true)
    @JsonBackReference
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<SeatBooked> bookedSeats;


    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, fetch = FetchType.LAZY , orphanRemoval=true)
    @JsonBackReference
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<Booking> bookings;





    @JsonBackReference
    @JsonProperty
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Set<Booking> getBookings() {
        return bookings;
    }

    @JsonBackReference
    @JsonProperty
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Set<SeatBooked> getBookedSeats() {
        return bookedSeats;
    }

    @JsonManagedReference
    @JsonProperty
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Movie getMovie() {
        return movie;
    }

    @JsonManagedReference
    @JsonProperty
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Hall getHall() {
        return hall;
    }

    @JsonManagedReference
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    @JsonManagedReference
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public void setHall(Hall hall) {
        this.hall = hall;
    }
    @JsonBackReference
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public void setBookedSeats(Set<SeatBooked> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }
    @JsonBackReference
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }
}
