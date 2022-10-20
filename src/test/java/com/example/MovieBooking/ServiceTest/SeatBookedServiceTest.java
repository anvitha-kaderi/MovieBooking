package com.example.MovieBooking.ServiceTest;


import com.example.MovieBooking.Repository.*;
import com.example.MovieBooking.Service.SeatBookedService;
import com.example.MovieBooking.model.*;
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
public class SeatBookedServiceTest {

    @Mock
    private SeatBookedRepo seatBookedRepo;

    @Mock
    private BookingRepo bookingRepo;

    @Mock
    private SeatRepo seatRepo;

    @Mock
    private ShowRepo showRepo;

    @Mock
    private PaymentRepo paymentRepo;

    @InjectMocks
    private SeatBookedService seatBookedService;

//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void NewSeatBookedTesting()
    {
        SeatBooked seatBooked=new SeatBooked();
        Seat seat=new Seat();
        seat.setId(1);
        seatBooked.setSeat(seat);
        Booking Booking=new Booking();
        Booking.setId(2);
        Booking.setBookingTime("02:02:02");
        seatBooked.setBooking(Booking);
        Show show=new Show();
        show.setId(5);
        Booking.setShow(show);
        Payment payment=new Payment();
        payment.setId(4);
        payment.setPaymentTime("04:04:04");
        //when(SeatBookedRepo.save(ArgumentMatchers.any(seatBooked.class))).thenReturn(seatBooked);
        given(seatBookedRepo.save(ArgumentMatchers.any(SeatBooked.class))).willReturn(seatBooked);
        given(paymentRepo.save(ArgumentMatchers.any(Payment.class))).willReturn(payment);
        given(bookingRepo.findById(2)).willReturn(Optional.of(Booking));
        given(seatRepo.findById(1)).willReturn(Optional.of(seat));
        given(showRepo.findById(5)).willReturn((Optional.of(show)));
        SeatBooked newSeatBooked= seatBookedService.addSeatBooked(1,2,5,seatBooked);
        assertThat(newSeatBooked).isSameAs(seatBooked);
        verify(seatBookedRepo).save(seatBooked);

    }

//    @Test
//    public void UpdateSeatBookedTesting()
//    {
//        SeatBooked oldSeatBooked=new SeatBooked();
//        oldSeatBooked.setId(1);
//        oldSeatBooked.setName("kantara");
//        SeatBooked newSeatBooked=new SeatBooked();
//        newSeatBooked.setId(1);
//        newSeatBooked.setName("rrr");
//        given(SeatBookedRepo.findById(oldSeatBooked.getId())).willReturn(Optional.of(oldSeatBooked));
//        SeatBookedService.updateSeatBooked(oldSeatBooked.getId(),newSeatBooked);
//        verify(SeatBookedRepo).save(newSeatBooked);
//        verify(SeatBookedRepo).findById(oldSeatBooked.getId());
//    }


    @Test
    public void GetSeatBookedTesting()
    {
        List<SeatBooked> SeatBookedList= new ArrayList<>();
        SeatBookedList.add(new SeatBooked());
        given(seatBookedRepo.findAll()).willReturn(SeatBookedList);
        List<SeatBooked> expected= seatBookedService.getSeatBooked();
        assertEquals(SeatBookedList,expected);
        verify(seatBookedRepo).findAll();
    }

    @Test
    public void DeleteSeatBookedTesting()
    {
        SeatBooked SeatBooked=new SeatBooked();
        SeatBooked.setId(1);
        when(seatBookedRepo.findById(SeatBooked.getId())).thenReturn(Optional.of(SeatBooked));
        seatBookedService.deleteSeatBooked(SeatBooked.getId());
        verify(seatBookedRepo).deleteById(SeatBooked.getId());
    }

}
