package com.example.MovieBooking.ControllerTest;


import com.example.MovieBooking.Controller.CustomerController;
import com.example.MovieBooking.MovieBookingApplication;
import com.example.MovieBooking.Service.CustomerService;
import com.example.MovieBooking.model.Customer;
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
@WebMvcTest(controllers= CustomerController.class)
@ActiveProfiles("staging")
//@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class })
//@SpringBootTest(classes = MovieBookingApplication.class)
public class CustomerControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService Service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void postCustomerTest() throws Exception {
        Customer customer=new Customer();
        customer.setName("Mangalore");
        customer.setEmailid("Karnataka@gmail.com");
        given(Service.addCustomer(customer)).willReturn(customer);
        ResultActions response=mockMvc.perform(post("/customer").contentType("application/json").content(objectMapper.writeValueAsString(customer)));
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(customer.getName())))
                .andExpect(jsonPath("$.emailid",is(customer.getEmailid())));

    }

    @Test
    public void getCustomertest() throws Exception{
        Customer customer1=new Customer();
        customer1.setName("mangalore");
        customer1.setEmailid("karnataka@gmail.com");
        List<Customer> CustomerList= new ArrayList<>();
        CustomerList.add(customer1);
        given(Service.getCustomer()).willReturn(CustomerList);
        ResultActions response=mockMvc.perform(get("/customers"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(CustomerList.size())));

    }

    @Test
    public void putCustomertesting() throws Exception {
        Customer Customer1=new Customer();
        Customer1.setId(1);
        Customer1.setName("mglore");
        Customer1.setEmailid("kar@gmail.com");
        given(Service.updateCustomer(1,Customer1)).willReturn(Customer1);
        mockMvc.perform(put("/customer/"+Integer.toString(Customer1.getId())).contentType("application/json").content(objectMapper.writeValueAsString(Customer1)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.name",is(Customer1.getName())))
                .andExpect(jsonPath("$.emailid",is(Customer1.getEmailid())));

    }

    @Test
    public void deleteCustomertesting() throws Exception
    {
        Customer Customer1=new Customer();
        Customer1.setName("mglore");
        Customer1.setEmailid("karnataka");
        Customer1.setId(1);
        doNothing().when(Service).deleteCustomer(Customer1.getId());
        mockMvc.perform(delete("/customer/"+Integer.toString(Customer1.getId())).contentType("application/json"))
                .andExpect(status().isAccepted());

    }

}