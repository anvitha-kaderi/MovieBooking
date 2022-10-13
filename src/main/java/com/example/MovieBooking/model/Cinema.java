package com.example.MovieBooking.model;

import com.fasterxml.jackson.annotation.*;
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


    @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true)
    @JsonBackReference
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    Set<Hall> halls;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    @JsonManagedReference
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private City city;

    @JsonBackReference
    @JsonProperty
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Set<Hall> getHalls() {
        return halls;
    }


    @JsonManagedReference
    @JsonProperty
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public City getCity() {
        return city;
    }

    @JsonIgnore
    @JsonManagedReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public void setCity(City city) {
        this.city = city;
    }

    @JsonBackReference
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public void setHalls(Set<Hall> halls) {
        this.halls = halls;
    }

}
