package com.example.MovieBooking.Service;

import com.example.MovieBooking.Error.ShowNotFoundException;
import com.example.MovieBooking.Repository.HallRepo;
import com.example.MovieBooking.Repository.MovieRepo;
import com.example.MovieBooking.Repository.ShowRepo;
import com.example.MovieBooking.model.Hall;
import com.example.MovieBooking.model.Movie;
import com.example.MovieBooking.model.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    @Autowired
    public ShowRepo ShowRepo;

    @Autowired
    public HallRepo hallRepo;

    @Autowired
    public MovieRepo movierepo;
    public List<Show> getShow() {
        return ShowRepo.findAll();
    }

    public Show addShow(int id1, int id2,Show show) {
        Optional<Hall>hall= hallRepo.findById(id1);
        Optional<Movie> movie=movierepo.findById(id2);
        if(hall.isEmpty() || movie.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Data already exists");
        }
        show.setHall(hall.get());
        show.setMovie(movie.get());
        ShowRepo.save(show);
        return show;
    }


    public Show updateShow(int id, Show show) {
        Optional<Show> c= Optional.ofNullable(ShowRepo.findById(id).orElseThrow(() -> new ShowNotFoundException(Integer.toString(id))));
        if(c.isEmpty())
        {
            throw new ShowNotFoundException(" Show Not found with id "+id);
        }
        Show newshow=c.get();
        newshow.setDate(show.getDate());
        newshow.setStart_time(show.getStart_time());
        newshow.setEnd_time(show.getEnd_time());
        ShowRepo.save(newshow);
        return newshow;
    }


    public void deleteShow(int id) {
        Optional<Show> show=ShowRepo.findById(id);
        if(show.isEmpty())
        {
            throw new ShowNotFoundException(" Show Not found with id "+id);
        }
        ShowRepo.deleteById(id);
    }
}
