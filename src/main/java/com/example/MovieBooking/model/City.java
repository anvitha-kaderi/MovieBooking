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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String cityname;
    private String state;
    @OneToMany(mappedBy = "city" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JsonBackReference
    @JsonIgnore
    private Set<Cinema> cinemas;

    @JsonBackReference
    @JsonIgnore
    public Set<Cinema> getCinemas() {
        return cinemas;
    }
}
