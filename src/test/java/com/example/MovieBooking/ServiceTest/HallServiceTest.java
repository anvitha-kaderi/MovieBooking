package com.example.MovieBooking.ServiceTest;


import com.example.MovieBooking.Repository.HallRepo;
import com.example.MovieBooking.Repository.CinemaRepo;
import com.example.MovieBooking.Service.HallService;
import com.example.MovieBooking.model.Hall;
import com.example.MovieBooking.model.Cinema;
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
public class HallServiceTest {

    @Mock
    private HallRepo hallRepo;

    @Mock
    private CinemaRepo cinemaRepo;

    @InjectMocks
    private HallService hallService;


    @Test
    public void NewHallTesting()
    {
        Hall hall=new Hall();
        hall.setHallno(1);
        hall.setSize(300);
        Cinema cinema=new Cinema();
        cinema.setId(2);
        cinema.setName("rrr");
        hall.setCinema(cinema);
        //when(HallRepo.save(ArgumentMatchers.any(hall.class))).thenReturn(hall);
        given(hallRepo.save(ArgumentMatchers.any(Hall.class))).willReturn(hall);
        given(cinemaRepo.findById(2)).willReturn(Optional.of(cinema));
        Hall newHall= hallService.addHall(2,hall);
        assertThat(newHall).isSameAs(hall);
        verify(hallRepo).save(hall);

    }

    @Test
    public void UpdateHallTesting()
    {
        Hall oldHall=new Hall();
        oldHall.setId(1);
        oldHall.setSize(300);
        oldHall.setHallno(1);
        Hall newHall=new Hall();
        newHall.setId(1);
        newHall.setSize(200);
        newHall.setHallno(1);
        given(hallRepo.findById(oldHall.getId())).willReturn(Optional.of(oldHall));
        hallService.updateHall(oldHall.getId(),newHall);
        verify(hallRepo).save(newHall);
        verify(hallRepo).findById(oldHall.getId());
    }


    @Test
    public void GetHallTesting()
    {
        List<Hall> halls= new ArrayList<>();
        halls.add(new Hall());
        given(hallRepo.findAll()).willReturn(halls);
        List<Hall> expected= hallService.getHall();
        assertEquals(halls,expected);
        verify(hallRepo).findAll();
    }

    @Test
    public void DeleteHallTesting()
    {
        Hall hall=new Hall();
        hall.setId(1);
        hall.setSize(1);
        hall.setHallno(200);
        when(hallRepo.findById(hall.getId())).thenReturn(Optional.of(hall));
        hallService.deleteHall(hall.getId());
        verify(hallRepo).deleteById(hall.getId());
    }

}
