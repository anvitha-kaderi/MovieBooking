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
public class HallServiceTest {

    @Mock
    private HallRepo HallRepo;

    @Mock
    private CinemaRepo CinemaRepo;

    @InjectMocks
    private HallService HallService;


    @Test
    public void NewHallTesting()
    {
        Hall Hall=new Hall();
        Hall.setHallno(1);
        Hall.setSize(300);
        Cinema Cinema=new Cinema();
        Cinema.setId(2);
        Cinema.setName("rrr");
        Hall.setCinema(Cinema);
        //when(HallRepo.save(ArgumentMatchers.any(Hall.class))).thenReturn(Hall);
        given(HallRepo.save(ArgumentMatchers.any(Hall.class))).willReturn(Hall);
        given(CinemaRepo.findById(2)).willReturn(Optional.of(Cinema));
        Hall newHall=HallService.addHall(2,Hall);
        assertThat(newHall).isSameAs(Hall);
        verify(HallRepo).save(Hall);

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
        given(HallRepo.findById(oldHall.getId())).willReturn(Optional.of(oldHall));
        HallService.updateHall(oldHall.getId(),newHall);
        verify(HallRepo).save(newHall);
        verify(HallRepo).findById(oldHall.getId());
    }


    @Test
    public void GetHallTesting()
    {
        List<Hall> HallList= new ArrayList<>();
        HallList.add(new Hall());
        given(HallRepo.findAll()).willReturn(HallList);
        List<Hall> expected=HallService.getHall();
        assertEquals(HallList,expected);
        verify(HallRepo).findAll();
    }

    @Test
    public void DeleteHallTesting()
    {
        Hall Hall=new Hall();
        Hall.setId(1);
        Hall.setSize(1);
        Hall.setHallno(200);
        when(HallRepo.findById(Hall.getId())).thenReturn(Optional.of(Hall));
        HallService.deleteHall(Hall.getId());
        verify(HallRepo).deleteById(Hall.getId());
    }

}
