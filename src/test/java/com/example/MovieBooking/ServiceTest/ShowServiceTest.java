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
public class ShowServiceTest {

    @Mock
    private ShowRepo ShowRepo;

    @Mock
    private MovieRepo MovieRepo;

    @Mock
    private HallRepo HallRepo;

    @InjectMocks
    private ShowService ShowService;

    

    @Test
    public void NewShowTesting()
    {
        Show Show=new Show();
        Show.setCost(300);
        Show.setStart_time("02:02:00");
        Show.setEnd_time("03:03:30");
        Movie Movie=new Movie();
        Movie.setId(2);
        Movie.setTitle("RRR");
        Movie.setGenre("horror");
        Show.setMovie(Movie);
        Hall hall=new Hall();
        hall.setSize(30);
        hall.setHallno(5);
        hall.setId(4);
        Show.setHall(hall);
        given(ShowRepo.save(ArgumentMatchers.any(Show.class))).willReturn(Show);
        given(MovieRepo.findById(2)).willReturn(Optional.of(Movie));
        given(HallRepo.findById(4)).willReturn(Optional.of(hall));
        Show newShow=ShowService.addShow(4,2,Show);
        assertThat(newShow).isSameAs(Show);
        verify(ShowRepo).save(Show);

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
        given(ShowRepo.findById(oldShow.getId())).willReturn(Optional.of(oldShow));
        ShowService.updateShow(oldShow.getId(),newShow);
        verify(ShowRepo).save(newShow);
        verify(ShowRepo).findById(oldShow.getId());
    }


    @Test
    public void GetShowTesting()
    {
        List<Show> ShowList= new ArrayList<>();
        ShowList.add(new Show());
        given(ShowRepo.findAll()).willReturn(ShowList);
        List<Show> expected=ShowService.getShow();
        assertEquals(ShowList,expected);
        verify(ShowRepo).findAll();
    }

    @Test
    public void DeleteShowTesting()
    {
        Show Show=new Show();
        Show.setId(1);
        Show.setCost(300);
        when(ShowRepo.findById(Show.getId())).thenReturn(Optional.of(Show));
        ShowService.deleteShow(Show.getId());
        verify(ShowRepo).deleteById(Show.getId());
    }

}
