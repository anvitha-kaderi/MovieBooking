package com.example.MovieBooking.ControllerTest;


import com.example.MovieBooking.Controller.CinemaController;
import com.example.MovieBooking.MovieBookingApplication;
import com.example.MovieBooking.Service.CinemaService;
import com.example.MovieBooking.model.Cinema;
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
@WebMvcTest(controllers= CinemaController.class)
@ActiveProfiles("staging")
//@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class })
//@SpringBootTest(classes = MovieBookingApplication.class)
public class CinemaControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CinemaService Service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void postCinemaTest() throws Exception {
        Cinema cinema=new Cinema();
        cinema.setName("Manginox");
        City city= new City();
        city.setId(1);
        city.setCityname("mglore");
        city.setState("karnataka");
        cinema.setCity(city);
        given(Service.addCinema(city.getId(),cinema)).willReturn(cinema);
        ResultActions response=mockMvc.perform(post("/city/"+Integer.toString(1)+"/cinema").contentType("application/json").content(objectMapper.writeValueAsString(cinema)));
        response.andDo(print())
                .andExpect(status().isCreated());
              //  .andExpect(jsonPath("$.name", is(cinema.getName())));

    }

    @Test
    public void getCinematest() throws Exception{
        Cinema Cinema1=new Cinema();
        Cinema1.setName("mangalore");
        List<Cinema> CinemaList= new ArrayList<>();
        CinemaList.add(Cinema1);
        given(Service.getCinema()).willReturn(CinemaList);
        ResultActions response=mockMvc.perform(get("/cinemas"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(CinemaList.size())));

    }

    @Test
    public void putCinematesting() throws Exception {
        Cinema Cinema1=new Cinema();
        Cinema1.setId(1);
        Cinema1.setName("mglore");
        given(Service.updateCinema(1,Cinema1)).willReturn(Cinema1);
        mockMvc.perform(put("/cinema/"+Integer.toString(Cinema1.getId())).contentType("application/json").content(objectMapper.writeValueAsString(Cinema1)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.name",is(Cinema1.getName())));

    }

    @Test
    public void deleteCinematesting() throws Exception
    {
        Cinema Cinema1=new Cinema();
        Cinema1.setName("mglore");
        Cinema1.setId(1);
        doNothing().when(Service).deleteCinema(Cinema1.getId());
        mockMvc.perform(delete("/cinema/"+Integer.toString(Cinema1.getId())).contentType("application/json"))
                .andExpect(status().isAccepted());

    }

}