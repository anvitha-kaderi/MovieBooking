package com.example.MovieBooking.ServiceTest;


import com.example.MovieBooking.Repository.PaymentRepo;
import com.example.MovieBooking.Service.PaymentService;
import com.example.MovieBooking.model.Payment;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;


import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private PaymentRepo PaymentRepo;


    @InjectMocks
    private PaymentService PaymentService;

//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }




    @Test
    public void GetPaymentTesting()
    {
        List<Payment> PaymentList= new ArrayList<>();
        PaymentList.add(new Payment());
        given(PaymentRepo.findAll()).willReturn(PaymentList);
        List<Payment> expected=PaymentService.getpayment();
        assertEquals(PaymentList,expected);
        verify(PaymentRepo).findAll();
    }

    @Test
    public void DeletePaymentTesting()
    {
        Payment Payment=new Payment();
        Payment.setId(1);
        Payment.setPaymentTime("02:02:02");
        when(PaymentRepo.findById(Payment.getId())).thenReturn(Optional.of(Payment));
        PaymentService.deletepayment(Payment.getId());
        verify(PaymentRepo).deleteById(Payment.getId());
    }

}
