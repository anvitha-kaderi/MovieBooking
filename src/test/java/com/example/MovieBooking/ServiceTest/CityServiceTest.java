package com.example.MovieBooking.ServiceTest;


import com.example.MovieBooking.Repository.CityRepo;
import com.example.MovieBooking.Service.CityService;
import com.example.MovieBooking.model.City;
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
public class CityServiceTest {

    @Mock
    private CityRepo cityRepo;


    @InjectMocks
    private CityService cityService;

//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void NewCityTesting()
    {
        City city=new City();
        city.setCityname("mangalore");
        city.setState("Karnataka");
        //when(cityRepo.save(ArgumentMatchers.any(City.class))).thenReturn(city);
        given(cityRepo.save(ArgumentMatchers.any(City.class))).willReturn(city);
        City newCity=cityService.addCity(city);
        assertThat(newCity).isSameAs(city);
        verify(cityRepo).save(city);

    }

    @Test
    public void UpdateCityTesting()
    {
        City oldCity=new City();
        oldCity.setId(1);
        oldCity.setCityname("Mangalore");
        oldCity.setState("Karnataka");
        City newCity=new City();
        newCity.setId(1);
        newCity.setCityname("Bangalore");
        newCity.setState("Karnataka");
        given(cityRepo.findById(oldCity.getId())).willReturn(Optional.of(oldCity));
        cityService.updateCity(oldCity.getId(),newCity);
        verify(cityRepo).save(newCity);
        verify(cityRepo).findById(oldCity.getId());
    }


    @Test
    public void GetCityTesting()
    {
        List<City> CityList= new ArrayList<>();
        CityList.add(new City());
        given(cityRepo.findAll()).willReturn(CityList);
        List<City> expected=cityService.getCity();
        assertEquals(CityList,expected);
        verify(cityRepo).findAll();
    }

    @Test
    public void DeleteCityTesting()
    {
        City city=new City();
        city.setId(1);
        city.setCityname("mangalore");
        city.setState("Karnataka");
        when(cityRepo.findById(city.getId())).thenReturn(Optional.of(city));
        cityService.deleteCity(city.getId());
        verify(cityRepo).deleteById(city.getId());
    }

}
