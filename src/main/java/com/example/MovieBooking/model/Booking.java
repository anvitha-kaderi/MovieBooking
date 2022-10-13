package com.example.MovieBooking.model;

import com.fasterxml.jackson.annotation.*;
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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Show show;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_cid")
    @JsonManagedReference
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Customer customer;

    @JsonFormat(pattern="HH:mm:ss")
    private String bookingTime;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL,fetch = FetchType.LAZY , orphanRemoval=true)
    @JsonBackReference
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<SeatBooked> bookedSeats;





    @JsonManagedReference
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Customer getCustomer() {
        return customer;
    }

    @JsonManagedReference
    @JsonProperty
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Show getShow() {
        return show;
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
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
