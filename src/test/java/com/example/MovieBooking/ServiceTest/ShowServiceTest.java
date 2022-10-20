package com.example.MovieBooking.ServiceTest;


import com.example.MovieBooking.Repository.HallRepo;
import com.example.MovieBooking.Repository.ShowRepo;
import com.example.MovieBooking.Repository.MovieRepo;
import com.example.MovieBooking.Service.ShowService;
import com.example.MovieBooking.model.Hall;
import com.example.MovieBooking.model.Show;
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
public class ShowServiceTest {

    @Mock
    private ShowRepo showRepo;

    @Mock
    private MovieRepo movieRepo;

    @Mock
    private HallRepo hallRepo;

    @InjectMocks
    private ShowService showService;

    

    @Test
    public void NewShowTesting()
    {
        Show show=new Show();
        show.setCost(300);
        show.setStart_time("02:02:00");
        show.setEnd_time("03:03:30");
        Movie movie=new Movie();
        movie.setId(2);
        movie.setTitle("RRR");
        movie.setGenre("horror");
        movie.setLanguage("telugu");
        show.setMovie(movie);
        Hall hall=new Hall();
        hall.setSize(30);
        hall.setHallno(5);
        hall.setId(4);
        show.setHall(hall);
        given(showRepo.save(ArgumentMatchers.any(Show.class))).willReturn(show);
        given(movieRepo.findById(2)).willReturn(Optional.of(movie));
        given(hallRepo.findById(4)).willReturn(Optional.of(hall));
        Show newShow= showService.addShow(4,2,show);
        assertThat(newShow).isSameAs(show);
        verify(showRepo).save(show);

    }

    @Test
    public void UpdateShowTesting()
    {
        Show oldShow=new Show();
        oldShow.setId(1);
        oldShow.setCost(400);
        Show newShow=new Show();
        newShow.setId(1);
        newShow.setCost(300);
        given(showRepo.findById(oldShow.getId())).willReturn(Optional.of(oldShow));
        showService.updateShow(oldShow.getId(),newShow);
        verify(showRepo).save(newShow);
        verify(showRepo).findById(oldShow.getId());
    }


    @Test
    public void GetShowTesting()
    {
        List<Show> ShowList= new ArrayList<>();
        ShowList.add(new Show());
        given(showRepo.findAll()).willReturn(ShowList);
        List<Show> expected= showService.getShow();
        assertEquals(ShowList,expected);
        verify(showRepo).findAll();
    }

    @Test
    public void DeleteShowTesting()
    {
        Show show=new Show();
        show.setId(1);
        show.setCost(300);
        when(showRepo.findById(show.getId())).thenReturn(Optional.of(show));
        showService.deleteShow(show.getId());
        verify(showRepo).deleteById(show.getId());
    }

}
