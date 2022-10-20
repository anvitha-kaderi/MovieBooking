package com.example.MovieBooking.ServiceTest;


import com.example.MovieBooking.Repository.PaymentRepo;
import com.example.MovieBooking.Service.PaymentService;
import com.example.MovieBooking.model.Payment;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;


import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

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
    private PaymentRepo paymentRepo;


    @InjectMocks
    private PaymentService paymentService;

    @Test
    public void GetPaymentTesting()
    {
        List<Payment> PaymentList= new ArrayList<>();
        PaymentList.add(new Payment());
        given(paymentRepo.findAll()).willReturn(PaymentList);
        List<Payment> expected= paymentService.getpayment();
        assertEquals(PaymentList,expected);
        verify(paymentRepo).findAll();
    }

    @Test
    public void DeletePaymentTesting()
    {
        Payment payment=new Payment();
        payment.setId(1);
        payment.setPaymentTime("02:02:02");
        when(paymentRepo.findById(payment.getId())).thenReturn(Optional.of(payment));
        paymentService.deletepayment(payment.getId());
        verify(paymentRepo).deleteById(payment.getId());
    }

}
