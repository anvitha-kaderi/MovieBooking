package com.example.MovieBooking.Controller;

import com.example.MovieBooking.Service.MovieService;
import com.example.MovieBooking.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    @Autowired
    public MovieService service;
    @GetMapping("/movie")
    public ResponseEntity<List<Movie>> Movielist()
    {
        List<Movie> c=service.get_Movie();
        return ResponseEntity.ok().body(c);
    }
    @GetMapping("/title/movie")
    public ResponseEntity<List<Movie>> Movietitle(@RequestParam String s)
    {
        List<Movie> c=service.getting("title",s);
        return ResponseEntity.ok().body(c);
    }
    @GetMapping("/lang/movie")
    public ResponseEntity<List<Movie>> Moviel(@RequestParam String s)
    {
        List<Movie> c=service.getting("Lang",s);
        return ResponseEntity.ok().body(c);
    }
    @GetMapping("/genre/movie")
    public ResponseEntity<List<Movie>> Movieg(@RequestParam String s)
    {
        List<Movie> c=service.getting("genre",s);
        return ResponseEntity.ok().body(c);
    }
    @GetMapping("/duration/movie")
    public ResponseEntity<List<Movie>> Movied(@RequestParam String s)
    {
        List<Movie> c=service.getting("duration",s);
        return ResponseEntity.ok().body(c);
    }

    @PostMapping("/movie")
    public ResponseEntity<HttpStatus> PostingMovies(@RequestBody Movie cust)
    {
        service.add_Movie(cust);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping("/movie/{id}")
    public ResponseEntity<HttpStatus> updatingMovie(@PathVariable("id") int id , @RequestBody Movie cust)
    {
        service.updating_Movie(id,cust);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    @DeleteMapping("/movie/{id}")
    public ResponseEntity<HttpStatus> deletingMovie(@PathVariable("id") int id)
    {
        service.deleleting_Movie(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
}

