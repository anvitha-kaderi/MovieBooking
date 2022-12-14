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
    @GetMapping("/movie")
    public ResponseEntity<List<Movie>> getMovieByTitle(@RequestParam(name="title") String name)
    {
        List<Movie> c=service.fetchingMovie("title", name);
        return ResponseEntity.ok().body(c);
    }
    @GetMapping("/title/movie")
    public ResponseEntity<List<Movie>> getMovieByLanguage(@RequestParam(name = "language") String name)
    {
        List<Movie> c=service.fetchingMovie("language",name);
        return ResponseEntity.ok().body(c);
    }
    @GetMapping("/genre/movie")
    public ResponseEntity<List<Movie>> getMovieByGenre(@RequestParam(name = "genre") String name)
    {
        List<Movie> c=service.fetchingMovie("genre",name);
        return ResponseEntity.ok().body(c);
    }
    @GetMapping("/duration/movie")
    public ResponseEntity<List<Movie>> getMovieByDuration(@RequestParam(name="duration") String name)
    {
        List<Movie> c=service.fetchingMovie("duration",name);
        return ResponseEntity.ok().body(c);
    }

    @PostMapping("/movie")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie)
    {
        Movie newmovie=service.addMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(newmovie);
    }

    @PutMapping("/movie/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") int id , @RequestBody Movie movie)
    {
        Movie updatedmovie=service.updateMovie(id,movie);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedmovie);
    }

    @DeleteMapping("/movie/{id}")
    public ResponseEntity<HttpStatus> clearMovie(@PathVariable("id") int id)
    {
        service.deleteMovie(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
}

