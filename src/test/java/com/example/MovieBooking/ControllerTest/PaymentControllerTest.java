package com.example.MovieBooking.ControllerTest;


import com.example.MovieBooking.Controller.PaymentController;
import com.example.MovieBooking.MovieBookingApplication;
import com.example.MovieBooking.Service.PaymentService;
import com.example.MovieBooking.model.Payment;
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
@WebMvcTest(controllers= PaymentController.class)
@ActiveProfiles("staging")
//@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class })
//@SpringBootTest(classes = MovieBookingApplication.class)
public class PaymentControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PaymentService Service;

    @Autowired
    private MockMvc mockMvc;



    @Test
    public void getPaymenttest() throws Exception{
        Payment payment=new Payment();
        payment.setPaymentTime("02:02:02");
        List<Payment> PaymentList= new ArrayList<>();
        PaymentList.add(payment);
        given(Service.getpayment()).willReturn(PaymentList);
        ResultActions response=mockMvc.perform(get("/payment"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(PaymentList.size())));

    }


    @Test
    public void deletePaymenttesting() throws Exception
    {
        Payment Payment1=new Payment();
        Payment1.setId(1);
        doNothing().when(Service).deletepayment(Payment1.getId());
        mockMvc.perform(delete("/payment/"+Integer.toString(Payment1.getId())).contentType("application/json"))
                .andExpect(status().isOk());

    }

}