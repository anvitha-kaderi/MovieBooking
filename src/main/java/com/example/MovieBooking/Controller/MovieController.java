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
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getMovies()
    {
        List<Movie> movies=service.getMovie();
        return ResponseEntity.ok().body(movies);
    }
    @GetMapping("/title/movie")
    public ResponseEntity<List<Movie>> getMovieByTitle(@RequestParam String name)
    {
        List<Movie> c=service.fetchingMovie("title", name);
        return ResponseEntity.ok().body(c);
    }
    @GetMapping("/lang/movie")
    public ResponseEntity<List<Movie>> getMovieByLanguage(@RequestParam String name)
    {
        List<Movie> c=service.fetchingMovie("Lang",name);
        return ResponseEntity.ok().body(c);
    }
    @GetMapping("/genre/movie")
    public ResponseEntity<List<Movie>> getMovieByGenre(@RequestParam String name)
    {
        List<Movie> c=service.fetchingMovie("genre",name);
        return ResponseEntity.ok().body(c);
    }
    @GetMapping("/duration/movie")
    public ResponseEntity<List<Movie>> getMovieByDuration(@RequestParam String name)
    {
        List<Movie> c=service.fetchingMovie("duration",name);
        return ResponseEntity.ok().body(c);
    }

    @PostMapping("/movie")
    public ResponseEntity<HttpStatus> saveMovie(@RequestBody Movie movie)
    {
        service.addMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping("/movie/{id}")
    public ResponseEntity<HttpStatus> updateMovie(@PathVariable("id") int id , @RequestBody Movie movie)
    {
        service.updateMovie(id,movie);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    @DeleteMapping("/movie/{id}")
    public ResponseEntity<HttpStatus> clearMovie(@PathVariable("id") int id)
    {
        service.deleteMovie(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
}

