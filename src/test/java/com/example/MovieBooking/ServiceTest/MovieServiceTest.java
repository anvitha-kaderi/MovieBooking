package com.example.MovieBooking.ServiceTest;


import com.example.MovieBooking.Repository.MovieRepo;
import com.example.MovieBooking.Service.MovieService;
import com.example.MovieBooking.model.Movie;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;


import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

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
    private MovieRepo movieRepo;


    @InjectMocks
    private MovieService movieService;

    @Test
    public void NewMovieTesting()
    {
        Movie movie=new Movie();
        movie.setTitle("rrr");
        movie.setDuration("02:02:09");
        movie.setGenre("horror");
        movie.setLanguage("telugu");
        movie.setReleasedate("02-02-2022");
        //when(MovieRepo.save(ArgumentMatchers.any(movie.class))).thenReturn(movie);
        given(movieRepo.save(ArgumentMatchers.any(Movie.class))).willReturn(movie);
        Movie newMovie= movieService.addMovie(movie);
        assertThat(newMovie).isSameAs(movie);
        verify(movieRepo).save(movie);

    }

    @Test
    public void UpdateMovieTesting()
    {
        Movie oldMovie=new Movie();
        oldMovie.setId(1);
        oldMovie.setTitle("rrr");
        oldMovie.setGenre("horror");
        oldMovie.setLanguage("telugu");
        oldMovie.setReleasedate("02-02-2022");
        Movie newMovie=new Movie();
        newMovie.setId(1);
        newMovie.setTitle("RRR");
        newMovie.setGenre("thriller");
        newMovie.setLanguage("telugu");
        newMovie.setReleasedate("02-02-2022");
        given(movieRepo.findById(oldMovie.getId())).willReturn(Optional.of(oldMovie));
        movieService.updateMovie(oldMovie.getId(),newMovie);
        verify(movieRepo).save(newMovie);
        verify(movieRepo).findById(oldMovie.getId());
    }


    @Test
    public void GetMovieTesting()
    {
        List<Movie> MovieList= new ArrayList<>();
        MovieList.add(new Movie());
        given(movieRepo.findAll()).willReturn(MovieList);
        List<Movie> expected= movieService.getMovie();
        assertEquals(MovieList,expected);
        verify(movieRepo).findAll();
    }

    @Test
    public void DeleteMovieTesting()
    {
        Movie movie=new Movie();
        movie.setId(1);
        movie.setTitle("rrr");
        movie.setGenre("thriller");
        movie.setLanguage("telugu");
        movie.setReleasedate("02-02-2022");
        when(movieRepo.findById(movie.getId())).thenReturn(Optional.of(movie));
        movieService.deleteMovie(movie.getId());
        verify(movieRepo).deleteById(movie.getId());
    }

}
