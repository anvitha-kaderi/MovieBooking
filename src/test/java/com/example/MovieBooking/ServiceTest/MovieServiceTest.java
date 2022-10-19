package com.example.MovieBooking.ServiceTest;


import com.example.MovieBooking.Repository.MovieRepo;
import com.example.MovieBooking.Service.MovieService;
import com.example.MovieBooking.model.Movie;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;


import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepo MovieRepo;


    @InjectMocks
    private MovieService MovieService;

    @Test
    public void NewMovieTesting()
    {
        Movie Movie=new Movie();
        Movie.setTitle("rrr");
        Movie.setDuration("02:02:09");
        Movie.setGenre("horror");
        //when(MovieRepo.save(ArgumentMatchers.any(Movie.class))).thenReturn(Movie);
        given(MovieRepo.save(ArgumentMatchers.any(Movie.class))).willReturn(Movie);
        Movie newMovie=MovieService.addMovie(Movie);
        assertThat(newMovie).isSameAs(Movie);
        verify(MovieRepo).save(Movie);

    }

    @Test
    public void UpdateMovieTesting()
    {
        Movie oldMovie=new Movie();
        oldMovie.setId(1);
        oldMovie.setTitle("rrr");
        oldMovie.setGenre("horror");
        Movie newMovie=new Movie();
        newMovie.setId(1);
        newMovie.setTitle("RRR");
        newMovie.setGenre("thriller");
        given(MovieRepo.findById(oldMovie.getId())).willReturn(Optional.of(oldMovie));
        MovieService.updateMovie(oldMovie.getId(),newMovie);
        verify(MovieRepo).save(newMovie);
        verify(MovieRepo).findById(oldMovie.getId());
    }


    @Test
    public void GetMovieTesting()
    {
        List<Movie> MovieList= new ArrayList<>();
        MovieList.add(new Movie());
        given(MovieRepo.findAll()).willReturn(MovieList);
        List<Movie> expected=MovieService.getMovie();
        assertEquals(MovieList,expected);
        verify(MovieRepo).findAll();
    }

    @Test
    public void DeleteMovieTesting()
    {
        Movie Movie=new Movie();
        Movie.setId(1);
        Movie.setTitle("rrr");
        Movie.setGenre("thriller");
        when(MovieRepo.findById(Movie.getId())).thenReturn(Optional.of(Movie));
        MovieService.deleteMovie(Movie.getId());
        verify(MovieRepo).deleteById(Movie.getId());
    }

}
