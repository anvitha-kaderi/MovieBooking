package com.example.MovieBooking.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="sid")
    private int id;

    private String title;
    private String language;
    private String genre;
    private String duration;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private String releasedate;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY , orphanRemoval=true)
    @JsonBackReference
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<Show> shows;


    @JsonBackReference
    @JsonProperty
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Set<Show> getShows() {
        return shows;
    }

    @JsonBackReference
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public void setShows(Set<Show> shows) {
        this.shows = shows;
    }
}
