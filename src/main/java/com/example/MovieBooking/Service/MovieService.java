package com.example.MovieBooking.Service;

import com.example.MovieBooking.Error.MovieNotFoundException;
import com.example.MovieBooking.Repository.MovieRepo;
import com.example.MovieBooking.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class MovieService {
    @Autowired
    public MovieRepo movieRepo;
    public List<Movie> get_Movie() {
        return movieRepo.findAll();
    }

    public  List<Movie> getting(String s, String v){
        List<Movie> c=new ArrayList<>();
        if(s=="title")
        {
           c= (List<Movie>) movieRepo.findAllByTitle(v).orElseThrow(() -> new MovieNotFoundException(v+" not found"));
        }
        else if(s=="lang")
        {
            c= (List<Movie>) movieRepo.findAllByLanguage(v).orElseThrow(()->new MovieNotFoundException("movie not found"));
        }
        else if(s=="genre")
        {
            c= (List<Movie>) movieRepo.findAllByGenre(v).orElseThrow(()->new MovieNotFoundException("movie not found"));
        }
        else
        {
          c= (List<Movie>) movieRepo.findAllByDuration(v).orElseThrow(()->new MovieNotFoundException("movie not found"));
        }
        return c;
    }
    public void add_Movie(Movie movie) {
        Optional<Movie> c=movieRepo.findByTitleAndLanguageAndGenreAndDuration(movie.getTitle(),movie.getLanguage(),movie.getGenre(),movie.getDuration());
        if(c.isPresent())
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Data already exists");
        }
        movieRepo.save(movie);
    }


    public void updating_Movie(int id, Movie movie) {
        Optional<Movie> c= Optional.ofNullable(movieRepo.findById(id).orElseThrow(() -> new MovieNotFoundException(Integer.toString(id))));
        if(c.isEmpty())
        {
            throw new MovieNotFoundException(" Movie Not found with id "+id);
        }
        Movie movieo=c.get();
        movieo.setTitle(movie.getTitle());
        movieo.setLanguage(movie.getLanguage());
        movieo.setGenre(movie.getGenre());
        movieo.setDuration(movie.getDuration());
        movieRepo.save(movieo);
    }


    public void deleleting_Movie(int id) {
        Optional<Movie> c=movieRepo.findById(id);
        if(c.isEmpty())
        {
            throw new MovieNotFoundException(" Movie Not found with id "+id);
        }
        movieRepo.deleteById(id);
    }
}
