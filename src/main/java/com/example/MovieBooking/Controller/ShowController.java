package com.example.MovieBooking.Controller;

import com.example.MovieBooking.Service.ShowService;
import com.example.MovieBooking.model.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShowController {
    @Autowired
    public ShowService service;
    @GetMapping("/shows")
    public ResponseEntity<List<Show>> getShows()
    {
        List<Show> shows=service.getShow();
        return ResponseEntity.ok().body(shows);
    }

    @PostMapping("hall/{id1}/movie/{id2}/show")
    public ResponseEntity<HttpStatus> saveShow(@PathVariable("id1") int id1, @PathVariable("id2") int id2, @RequestBody Show show)
    {
        service.addShow(id1,id2,show);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping("/show/{id}")
    public ResponseEntity<HttpStatus> updateShow(@PathVariable("id") int id , @RequestBody Show show)
    {
        service.updateShow(id,show);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    @DeleteMapping("/show/{id}")
    public ResponseEntity<HttpStatus> clearShow(@PathVariable("id") int id)
    {
        service.deleteShow(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
}

