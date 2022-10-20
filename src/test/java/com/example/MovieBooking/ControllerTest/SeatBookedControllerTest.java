package com.example.MovieBooking.ControllerTest;


import com.example.MovieBooking.Controller.SeatBookedController;
import com.example.MovieBooking.MovieBookingApplication;
import com.example.MovieBooking.Service.SeatBookedService;
import com.example.MovieBooking.model.*;
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
@WebMvcTest(controllers= SeatBookedController.class)
public class SeatBookedControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SeatBookedService Service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void postSeatBookedTest() throws Exception {
        SeatBooked seatBooked=new SeatBooked();
        Seat seat=new Seat();
        seat.setId(1);
        seatBooked.setSeat(seat);
        Booking booking=new Booking();
        booking.setId(2);
        booking.setBookingTime("02:02:02");
        seatBooked.setBooking(booking);
        Show show=new Show();
        show.setId(5);
        booking.setShow(show);
        Payment payment=new Payment();
        payment.setId(4);
        payment.setPaymentTime("04:04:04");
        given(Service.addSeatBooked(seat.getId(),show.getId(),booking.getId(),seatBooked)).willReturn(seatBooked);
        ResultActions response=mockMvc.perform(post("/seat/"+Integer.toString(1)+"/book/"+Integer.toString(2)+"/show/"+Integer.toString(5)+"/bookseat").contentType("application/json").content(objectMapper.writeValueAsString(seatBooked)));
        response.andDo(print())
                .andExpect(status().isCreated());
        //  .andExpect(jsonPath("$.name", is(seatBooked.getName())));

    }

    @Test
    public void getSeatBookedtest() throws Exception{
        SeatBooked seatBooked=new SeatBooked();
        seatBooked.setId(1);
        List<SeatBooked> SeatBookedList= new ArrayList<>();
        SeatBookedList.add(seatBooked);
        given(Service.getSeatBooked()).willReturn(SeatBookedList);
        ResultActions response=mockMvc.perform(get("/seatsbooked"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(SeatBookedList.size())));

    }



    @Test
    public void deleteSeatBookedtesting() throws Exception
    {
        SeatBooked SeatBooked1=new SeatBooked();
        SeatBooked1.setId(1);
        doNothing().when(Service).deleteSeatBooked(SeatBooked1.getId());
        mockMvc.perform(delete("/seatbooked/"+Integer.toString(SeatBooked1.getId())).contentType("application/json"))
                .andExpect(status().isAccepted());

    }

}