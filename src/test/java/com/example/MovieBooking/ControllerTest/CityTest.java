//package com.example.MovieBooking.ControllerTest;
//
//
//import com.example.MovieBooking.Controller.CityController;
//import com.example.MovieBooking.Service.CityService;
//import com.example.MovieBooking.model.City;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import org.junit.jupiter.api.Test;
//
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.BDDMockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//
//@WebMvcTest(controllers = CityController.class)
//@RunWith(SpringRunner.class)
//
//public class CityTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private CityService cityService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    public void givenEmployeeObject_whenCreateEmployee_thenReturnSavedEmployee() throws Exception{
//
//        // given - precondition or setup
//        City employee = City.builder()
//                .cityname("Mgloe")
//                .state("kar")
//                .build();
//        //given(cityService.addCity(any(City.class)))
//           //     .willAnswer((invocation)-> invocation.getArgument(0));
//
//        given(cityService.addCity(employee)).willReturn(employee);
//        // when - action or behaviour that we are going test
//        ResultActions response = mockMvc.perform(post("/city")
//                .contentType("application/json")
//                .content(objectMapper.writeValueAsString(employee)));
//
//        response.andDo(print()).
//                andExpect(status().isCreated())
//                .andExpect(jsonPath("$.cityname",
//                        is(employee.getCityname())))
//                .andExpect(jsonPath("$.state",
//                        is(employee.getState())));
//
//    }
//}