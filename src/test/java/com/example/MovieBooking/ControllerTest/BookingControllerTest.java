package com.example.MovieBooking.ControllerTest;


import com.example.MovieBooking.Controller.BookingController;
import com.example.MovieBooking.MovieBookingApplication;
import com.example.MovieBooking.Service.BookingService;
import com.example.MovieBooking.model.Booking;
import com.example.MovieBooking.model.City;
import com.example.MovieBooking.model.Customer;
import com.example.MovieBooking.model.Show;
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
@WebMvcTest(controllers= BookingController.class)
@ActiveProfiles("staging")
//@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class })
//@SpringBootTest(classes = MovieBookingApplication.class)
public class BookingControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookingService Service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void postBookingTest() throws Exception {
        Booking booking=new Booking();
        booking.setBookingTime("02:02:02");
        Customer customer=new Customer();
        customer.setId(2);
        booking.setCustomer(customer);
        Show show=new Show();
        show.setId(3);
        show.setCost(300);
        booking.setShow(show);
        given(Service.addBooking(customer.getId(),show.getId(),booking)).willReturn(booking);
        ResultActions response=mockMvc.perform(post("/customer/"+Integer.toString(2)+"/book").param("show",Integer.toString(3)).contentType("application/json").content(objectMapper.writeValueAsString(booking)));
        response.andDo(print())
                .andExpect(status().isCreated());


    }

    @Test
    public void getBookingtest() throws Exception{
        Booking Booking1=new Booking();
        List<Booking> BookingList= new ArrayList<>();
        BookingList.add(Booking1);
        given(Service.getBooking()).willReturn(BookingList);
        ResultActions response=mockMvc.perform(get("/bookings"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(BookingList.size())));

    }


    @Test
    public void deleteBookingtesting() throws Exception
    {
        Booking Booking1=new Booking();
        Booking1.setId(1);
        doNothing().when(Service).deleteBooking(Booking1.getId());
        mockMvc.perform(delete("/book/"+Integer.toString(Booking1.getId())).contentType("application/json"))
                .andExpect(status().isAccepted());

    }

}