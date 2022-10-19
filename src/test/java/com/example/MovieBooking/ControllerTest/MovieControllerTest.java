package com.example.MovieBooking.ControllerTest;


import com.example.MovieBooking.Controller.MovieController;
import com.example.MovieBooking.MovieBookingApplication;
import com.example.MovieBooking.Service.MovieService;
import com.example.MovieBooking.model.Movie;
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
@WebMvcTest(controllers= MovieController.class)
@ActiveProfiles("staging")
//@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class })
//@SpringBootTest(classes = MovieBookingApplication.class)
public class MovieControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MovieService Service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void postMovieTest() throws Exception {
        Movie movie=new Movie();
        movie.setTitle("kantara");
        movie.setGenre("thriller");
        given(Service.addMovie(movie)).willReturn(movie);
        ResultActions response=mockMvc.perform(post("/movie").contentType("application/json").content(objectMapper.writeValueAsString(movie)));
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is(movie.getTitle())))
                .andExpect(jsonPath("$.genre",is(movie.getGenre())));

    }

    @Test
    public void getMovietest() throws Exception{
        Movie movie=new Movie();
        movie.setTitle("kantara");
        movie.setGenre("thriller");
        List<Movie> MovieList= new ArrayList<>();
        MovieList.add(movie);
        given(Service.getMovie()).willReturn(MovieList);
        ResultActions response=mockMvc.perform(get("/movies"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(MovieList.size())));

    }

    @Test
    public void putMovietesting() throws Exception {
        Movie movie=new Movie();
        movie.setTitle("kantara");
        movie.setGenre("thriller");
        movie.setId(1);
        given(Service.updateMovie(1,movie)).willReturn(movie);
        mockMvc.perform(put("/movie/"+Integer.toString(movie.getId())).contentType("application/json").content(objectMapper.writeValueAsString(movie)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.title",is(movie.getTitle())))
                .andExpect(jsonPath("$.genre",is(movie.getGenre())));

    }

    @Test
    public void deleteMovietesting() throws Exception
    {
        Movie movie=new Movie();
        movie.setTitle("kantara");
        movie.setGenre("thriller");
        movie.setId(1);
        doNothing().when(Service).deleteMovie(movie.getId());
        mockMvc.perform(delete("/movie/"+Integer.toString(movie.getId())).contentType("application/json"))
                .andExpect(status().isAccepted());

    }

}