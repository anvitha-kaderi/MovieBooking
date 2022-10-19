package com.example.MovieBooking.ControllerTest;


import com.example.MovieBooking.Controller.ShowController;
import com.example.MovieBooking.MovieBookingApplication;
import com.example.MovieBooking.Service.ShowService;
import com.example.MovieBooking.model.Hall;
import com.example.MovieBooking.model.Movie;
import com.example.MovieBooking.model.Show;
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
@WebMvcTest(controllers= ShowController.class)
@ActiveProfiles("staging")
//@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class })
//@SpringBootTest(classes = MovieBookingApplication.class)
public class ShowControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ShowService Service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void postShowTest() throws Exception {
        Show show=new Show();
        show.setCost(300);
        show.setStart_time("02:02:00");
        show.setEnd_time("03:03:30");
        Movie movie=new Movie();
        movie.setId(2);
        movie.setTitle("RRR");
        movie.setGenre("horror");
        show.setMovie(movie);
        Hall hall=new Hall();
        hall.setSize(30);
        hall.setHallno(5);
        hall.setId(4);
        show.setHall(hall);
        given(Service.addShow(hall.getId(), movie.getId(),show)).willReturn(show);
        ResultActions response=mockMvc.perform(post("/hall/"+Integer.toString(4)+"/movie/"+Integer.toString(2)+"/show").contentType("application/json").content(objectMapper.writeValueAsString(show)));
        response.andDo(print())
                .andExpect(status().isCreated());
        //  .andExpect(jsonPath("$.name", is(show.getName())));

    }

    @Test
    public void getShowtest() throws Exception{
        Show show=new Show();
        show.setCost(300);
        List<Show> ShowList= new ArrayList<>();
        ShowList.add(show);
        given(Service.getShow()).willReturn(ShowList);
        ResultActions response=mockMvc.perform(get("/shows"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(ShowList.size())));

    }

    @Test
    public void putShowtesting() throws Exception {
        Show show=new Show();
        show.setId(1);
        show.setCost(300);
        given(Service.updateShow(1,show)).willReturn(show);
        mockMvc.perform(put("/show/"+Integer.toString(show.getId())).contentType("application/json").content(objectMapper.writeValueAsString(show)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.cost",is(show.getCost())));

    }

    @Test
    public void deleteShowtesting() throws Exception
    {
        Show Show1=new Show();
        Show1.setCost(400);
        Show1.setId(1);
        doNothing().when(Service).deleteShow(Show1.getId());
        mockMvc.perform(delete("/show/"+Integer.toString(Show1.getId())).contentType("application/json"))
                .andExpect(status().isAccepted());

    }

}