package com.example.MovieBooking.ServiceTest;


import com.example.MovieBooking.Repository.CinemaRepo;
import com.example.MovieBooking.Repository.CityRepo;
import com.example.MovieBooking.Service.CinemaService;
import com.example.MovieBooking.model.Cinema;
import com.example.MovieBooking.model.City;
import org.checkerframework.checker.units.qual.C;
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
public class CinemaServiceTest {

    @Mock
    private CinemaRepo CinemaRepo;

    @Mock
    private CityRepo cityRepo;

    @InjectMocks
    private CinemaService CinemaService;

//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void NewCinemaTesting()
    {
        Cinema cinema=new Cinema();
        cinema.setName("Kantara");
        City city=new City();
        city.setId(2);
        city.setCityname("Mangalore");
        city.setState("karnataka");
        cinema.setCity(city);
        //when(CinemaRepo.save(ArgumentMatchers.any(Cinema.class))).thenReturn(Cinema);
        given(CinemaRepo.save(ArgumentMatchers.any(Cinema.class))).willReturn(cinema);
        given(cityRepo.findById(2)).willReturn(Optional.of(city));
        Cinema newCinema=CinemaService.addCinema(2,cinema);
        assertThat(newCinema).isSameAs(cinema);
        verify(CinemaRepo).save(cinema);

    }

    @Test
    public void UpdateCinemaTesting()
    {
        Cinema oldCinema=new Cinema();
        oldCinema.setId(1);
        oldCinema.setName("kantara");
        Cinema newCinema=new Cinema();
        newCinema.setId(1);
        newCinema.setName("rrr");
        given(CinemaRepo.findById(oldCinema.getId())).willReturn(Optional.of(oldCinema));
        CinemaService.updateCinema(oldCinema.getId(),newCinema);
        verify(CinemaRepo).save(newCinema);
        verify(CinemaRepo).findById(oldCinema.getId());
    }


    @Test
    public void GetCinemaTesting()
    {
        List<Cinema> CinemaList= new ArrayList<>();
        CinemaList.add(new Cinema());
        given(CinemaRepo.findAll()).willReturn(CinemaList);
        List<Cinema> expected=CinemaService.getCinema();
        assertEquals(CinemaList,expected);
        verify(CinemaRepo).findAll();
    }

    @Test
    public void DeleteCinemaTesting()
    {
        Cinema Cinema=new Cinema();
        Cinema.setId(1);
        Cinema.setName("rrr");
        when(CinemaRepo.findById(Cinema.getId())).thenReturn(Optional.of(Cinema));
        CinemaService.deleteCinema(Cinema.getId());
        verify(CinemaRepo).deleteById(Cinema.getId());
    }

}
