package com.example.MovieBooking.Service;

import com.example.MovieBooking.Error.MovieNotFoundException;
import com.example.MovieBooking.Repository.MovieRepo;
import com.example.MovieBooking.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Service
public class MovieService {
    @Autowired
    public MovieRepo movieRepo;
    public List<Movie> getMovie() {
        return movieRepo.findAll();
    }

    public  List<Movie> fetchingMovie(String s, String v){

        if(s=="title")
        {
           Optional<List<Movie>> movies=movieRepo.findAllByTitle(v);
           if(movies.isEmpty())
               throw new MovieNotFoundException("movies not found");
           return movies.get();
        }
        else if(s=="language")
        {
            Optional<List<Movie>> movies=movieRepo.findAllByLanguage(v);
            if(movies.isEmpty())
                throw new MovieNotFoundException("movies not found");
            return movies.get();
        }
        else if(s=="genre")
        {
            Optional<List<Movie>> movies=movieRepo.findAllByGenre(v);
            if(movies.isEmpty())
                throw new MovieNotFoundException("movies not found");
            return movies.get();
        }
        else
        {
          Optional<List<Movie>> movies=movieRepo.findAllByDuration(v);
            if(movies.isEmpty())
                throw new MovieNotFoundException("movies not found");
          return movies.get();
        }

    }
    public Movie addMovie(Movie movie) {
        Optional<Movie> movie1=movieRepo.findByTitleAndLanguageAndGenreAndDuration(movie.getTitle(),movie.getLanguage(),movie.getGenre(),movie.getDuration());
        if(movie1.isPresent())
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Data already exists");
        }
        movieRepo.save(movie);
        return movie;
    }


    public Movie updateMovie(int id, Movie movie) {
        Optional<Movie> movie1= Optional.ofNullable(movieRepo.findById(id).orElseThrow(() -> new MovieNotFoundException(Integer.toString(id))));
        if(movie1.isEmpty())
        {
            throw new MovieNotFoundException(" Movie Not found with id "+id);
        }
        Movie newmovie=movie1.get();
        newmovie.setTitle(movie.getTitle());
        newmovie.setLanguage(movie.getLanguage());
        newmovie.setGenre(movie.getGenre());
        newmovie.setDuration(movie.getDuration());
        movieRepo.save(newmovie);
        return newmovie;
    }


    public void deleteMovie(int id) {
        Optional<Movie> movie=movieRepo.findById(id);
        if(movie.isEmpty())
        {
            throw new MovieNotFoundException(" Movie Not found with id "+id);
        }
        movieRepo.deleteById(id);
    }
}
