package com.example.MovieBooking.ControllerTest;


import com.example.MovieBooking.Controller.HallController;
import com.example.MovieBooking.MovieBookingApplication;
import com.example.MovieBooking.Service.HallService;
import com.example.MovieBooking.model.Hall;
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
@WebMvcTest(controllers= HallController.class)
@ActiveProfiles("staging")
//@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class })
//@SpringBootTest(classes = MovieBookingApplication.class)
public class HallControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private HallService Service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void postHallTest() throws Exception {
        Hall hall=new Hall();
        hall.setHallno(30);
        hall.setSize(200);
        Cinema cinema= new Cinema();
        cinema.setId(1);
        cinema.setName("inox");
        hall.setCinema(cinema);
        given(Service.addHall(cinema.getId(),hall)).willReturn(hall);
        ResultActions response=mockMvc.perform(post("/cinema/"+Integer.toString(1)+"/hall").contentType("application/json").content(objectMapper.writeValueAsString(hall)));
        response.andDo(print())
                .andExpect(status().isCreated());


    }

    @Test
    public void getHalltest() throws Exception{
        Hall hall1=new Hall();
        hall1.setSize(300);
        hall1.setHallno(1);
        List<Hall> HallList= new ArrayList<>();
        HallList.add(hall1);
        given(Service.getHall()).willReturn(HallList);
        ResultActions response=mockMvc.perform(get("/halls"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(HallList.size())));

    }

    @Test
    public void putHalltesting() throws Exception {
        Hall hall=new Hall();
        hall.setId(1);
        hall.setHallno(20);
        hall.setSize(300);
        given(Service.updateHall(1,hall)).willReturn(hall);
        mockMvc.perform(put("/hall/"+Integer.toString(hall.getId())).contentType("application/json").content(objectMapper.writeValueAsString(hall)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.hallno",is(hall.getHallno())))
                .andExpect(jsonPath("$.size",is(hall.getSize())));
    }

    @Test
    public void deleteHalltesting() throws Exception
    {
        Hall Hall1=new Hall();
        Hall1.setHallno(2);
        Hall1.setSize(200);
        Hall1.setId(1);
        doNothing().when(Service).deleteHall(Hall1.getId());
        mockMvc.perform(delete("/hall/"+Integer.toString(Hall1.getId())).contentType("application/json"))
                .andExpect(status().isAccepted());

    }

}