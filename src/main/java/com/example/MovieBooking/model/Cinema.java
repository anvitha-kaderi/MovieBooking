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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cinema")
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cid")
    private int id;

    @Column(name ="cinema_name")
    private String name;


    @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    @JsonIgnore
    Set<Hall> halls;

    @JsonBackReference
    @JsonIgnore
    public Set<Hall> getHalls() {
        return halls;
    }


    @JsonManagedReference
    @JsonIgnore
    public City getCity() {
        return city;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    @JsonManagedReference
   @JsonIgnore
    private City city;

}
