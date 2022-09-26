package com.example.MovieBooking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name="hall")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hid")
    private int id;

    private int hallno;
    private int size;

    public int getHallno() {
        return hallno;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_cid")
    @JsonManagedReference
    @JsonIgnore
    public Cinema getCinema() {
        return cinema;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_cid")
    @JsonManagedReference
    @JsonIgnore
    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public void setHallno(int hallno) {
        this.hallno = hallno;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_cid")
    @JsonManagedReference
    @JsonIgnore
    private Cinema cinema;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    @JsonIgnore
    private Set<Seat> seats;
    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    @JsonIgnore
    public Set<Seat> getSeats() {
        return seats;
    }
}
