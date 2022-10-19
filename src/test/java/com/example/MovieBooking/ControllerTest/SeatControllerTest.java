package com.example.MovieBooking.ControllerTest;


import com.example.MovieBooking.Controller.SeatController;
import com.example.MovieBooking.MovieBookingApplication;
import com.example.MovieBooking.Service.SeatService;
import com.example.MovieBooking.model.Hall;
import com.example.MovieBooking.model.Seat;
import com.example.MovieBooking.model.Cinema;
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
@WebMvcTest(controllers= SeatController.class)
@ActiveProfiles("staging")
//@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class })
//@SpringBootTest(classes = MovieBookingApplication.class)
public class SeatControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SeatService Service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void postSeatTest() throws Exception {
        Seat seat=new Seat();
        seat.setNo(2);
//        Cinema cinema= new Cinema();
//        cinema.setId(1);
//        cinema.setName("mglore");
        Hall hall = new Hall();
        hall.setId(2);
        given(Service.addSeat(hall.getId(),seat)).willReturn(seat);
        ResultActions response=mockMvc.perform(post("/hall/"+Integer.toString(2)+"/seat").contentType("application/json").content(objectMapper.writeValueAsString(seat)));
        response.andDo(print())
                .andExpect(status().isCreated());
        //  .andExpect(jsonPath("$.name", is(seat.getName())));

    }

    @Test
    public void getSeattest() throws Exception{
        Seat Seat1=new Seat();
        Seat1.setNo(20);
        List<Seat> SeatList= new ArrayList<>();
        SeatList.add(Seat1);
        given(Service.getSeat()).willReturn(SeatList);
        ResultActions response=mockMvc.perform(get("/seats"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(SeatList.size())));

    }

    @Test
    public void putSeattesting() throws Exception {
        Seat Seat1=new Seat();
        Seat1.setId(1);
        given(Service.updateSeat(1,Seat1)).willReturn(Seat1);
        mockMvc.perform(put("/seat/"+Integer.toString(Seat1.getId())).contentType("application/json").content(objectMapper.writeValueAsString(Seat1)))
                .andExpect(status().isAccepted());


    }

    @Test
    public void deleteSeattesting() throws Exception
    {
        Seat Seat1=new Seat();

        Seat1.setId(1);
        doNothing().when(Service).deleteSeat(Seat1.getId());
        mockMvc.perform(delete("/seat/"+Integer.toString(Seat1.getId())).contentType("application/json"))
                .andExpect(status().isAccepted());

    }

}