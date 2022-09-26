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
    public ResponseEntity<List<Show>> Showlist()
    {
        List<Show> c=service.get_Show();
        return ResponseEntity.ok().body(c);
    }

    @PostMapping("hall/{id1}/movie/{id2}/show")
    public ResponseEntity<HttpStatus> PostingShows(@PathVariable("id1") int id1, @PathVariable("id2") int id2, @RequestBody Show cust)
    {
        service.add_Show(id1,id2,cust);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping("/show/{id}")
    public ResponseEntity<HttpStatus> updatingShow(@PathVariable("id") int id , @RequestBody Show cust)
    {
        service.updating_Show(id,cust);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    @DeleteMapping("/show/{id}")
    public ResponseEntity<HttpStatus> deletingShow(@PathVariable("id") int id)
    {
        service.deleleting_Show(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
}

