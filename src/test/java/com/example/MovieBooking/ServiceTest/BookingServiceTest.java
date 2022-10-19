package com.example.MovieBooking.ServiceTest;


import com.example.MovieBooking.Repository.BookingRepo;
import com.example.MovieBooking.Repository.CityRepo;
import com.example.MovieBooking.Repository.CustomerRepo;
import com.example.MovieBooking.Repository.ShowRepo;
import com.example.MovieBooking.Service.BookingService;
import com.example.MovieBooking.model.Booking;
import com.example.MovieBooking.model.City;
import com.example.MovieBooking.model.Customer;
import com.example.MovieBooking.model.Show;
import org.checkerframework.checker.units.qual.C;
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
public class BookingServiceTest {

    @Mock
    private BookingRepo BookingRepo;

    @Mock
    private CustomerRepo customerRepo;

    @Mock
    private ShowRepo showRepo;

    @InjectMocks
    private BookingService BookingService;

//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void NewBookingTesting()
    {
        Booking Booking=new Booking();
        Booking.setBookingTime("02:02:02");
        Customer customer=new Customer();
        customer.setId(2);
        Booking.setCustomer(customer);
        Show show=new Show();
        show.setId(3);
        show.setCost(300);
        Booking.setShow(show);
        //when(BookingRepo.save(ArgumentMatchers.any(Booking.class))).thenReturn(Booking);
        given(BookingRepo.save(ArgumentMatchers.any(Booking.class))).willReturn(Booking);
        given(customerRepo.findById(2)).willReturn(Optional.of(customer));
        given(showRepo.findById(3)).willReturn(Optional.of(show));
        Booking newBooking=BookingService.addBooking(2,3,Booking);
        assertThat(newBooking).isSameAs(Booking);
        verify(BookingRepo).save(Booking);

    }

//    @Test
//    public void UpdateBookingTesting()
//    {
//        Booking oldBooking=new Booking();
//        oldBooking.setId(1);
//        oldBooking.setBookingTime("02:02:00");
//        Booking newBooking=new Booking();
//        newBooking.setId(1);
//        newBooking.setBookingTime("02:02:02");
//        given(BookingRepo.findById(oldBooking.getId())).willReturn(Optional.of(oldBooking));
//        BookingService.updateBooking(oldBooking.getId(),newBooking);
//        verify(BookingRepo).save(newBooking);
//        verify(BookingRepo).findById(oldBooking.getId());
//    }


    @Test
    public void GetBookingTesting()
    {
        List<Booking> BookingList= new ArrayList<>();
        BookingList.add(new Booking());
        given(BookingRepo.findAll()).willReturn(BookingList);
        List<Booking> expected=BookingService.getBooking();
        assertEquals(BookingList,expected);
        verify(BookingRepo).findAll();
    }

    @Test
    public void DeleteBookingTesting()
    {
        Booking Booking=new Booking();
        Booking.setId(1);
        Booking.setBookingTime("02:02:02");
        when(BookingRepo.findById(Booking.getId())).thenReturn(Optional.of(Booking));
        BookingService.deleteBooking(Booking.getId());
        verify(BookingRepo).deleteById(Booking.getId());
    }

}
