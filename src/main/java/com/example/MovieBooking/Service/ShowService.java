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
    public List<Show> get_Show() {
        return ShowRepo.findAll();
    }

    public void add_Show(int id1, int id2,Show cine) {
        //Optional<Show> c=ShowRepo.findByDateAndStart_timeAndEnd_time(cine.getDate(),cine.getStart_time(),cine.getEnd_time());
        Optional<Hall>cy= hallRepo.findById(id1);
        Optional<Movie> my=movierepo.findById(id2);
        if(cy.isEmpty() || my.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Data already exists");
        }
        cine.setHall(cy.get());
        cine.setMovie(my.get());
        ShowRepo.save(cine);
    }


    public void updating_Show(int id, Show cust) {
        Optional<Show> c= Optional.ofNullable(ShowRepo.findById(id).orElseThrow(() -> new ShowNotFoundException(Integer.toString(id))));
        if(c.isEmpty())
        {
            throw new ShowNotFoundException(" Show Not found with id "+id);
        }
        Show custo=c.get();
        custo.setDate(cust.getDate());
        custo.setStart_time(cust.getStart_time());
        custo.setEnd_time(cust.getEnd_time());
        ShowRepo.save(custo);
    }


    public void deleleting_Show(int id) {
        Optional<Show> c=ShowRepo.findById(id);
        if(c.isEmpty())
        {
            throw new ShowNotFoundException(" Show Not found with id "+id);
        }
        ShowRepo.deleteById(id);
    }
}
