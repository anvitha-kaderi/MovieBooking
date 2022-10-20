package com.example.MovieBooking.ControllerTest;


import com.example.MovieBooking.Controller.CityController;
import com.example.MovieBooking.MovieBookingApplication;
import com.example.MovieBooking.Service.CityService;
import com.example.MovieBooking.model.City;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers= CityController.class)

public class CityControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CityService Service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void postCityTest() throws Exception {
        City city=new City();
        city.setCityname("Mangalore");
        city.setState("Karnataka");
        given(Service.addCity(city)).willReturn(city);
        ResultActions response=mockMvc.perform(post("/city").contentType("application/json").content(objectMapper.writeValueAsString(city)));
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cityname", is(city.getCityname())))
                .andExpect(jsonPath("$.state",is(city.getState())));

    }

    @Test
    public void getCitytest() throws Exception{
        City city1=new City();
        city1.setCityname("mangalore");
        city1.setState("karnataka");
        City city2=new City();
        city2.setCityname("bangalore");
        city2.setState("Karnataka");
        List<City> cityList= new ArrayList<>();
        cityList.add(city1);
        cityList.add(city2);
        given(Service.getCity()).willReturn(cityList);
        ResultActions response=mockMvc.perform(get("/cities"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(cityList.size())));

    }

    @Test
    public void putcitytesting() throws Exception {
        City city1=new City();
        city1.setId(1);
        city1.setCityname("mglore");
        city1.setState("kar");
        City city2=new City();
        city1.setState("karnataka");
        city1.setCityname("bglore");
        given(Service.updateCity(1,city1)).willReturn(city1);
        mockMvc.perform(put("/city/"+Integer.toString(city1.getId())).contentType("application/json").content(objectMapper.writeValueAsString(city1)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.cityname",is(city1.getCityname())));

    }

    @Test
    public void deletecitytesting() throws Exception
    {
        City city1=new City();
        city1.setCityname("mglore");
        city1.setState("karnataka");
        city1.setId(1);
        doNothing().when(Service).deleteCity(city1.getId());
        mockMvc.perform(delete("/city/"+Integer.toString(city1.getId())).contentType("application/json"))
                .andExpect(status().isAccepted());

    }

}