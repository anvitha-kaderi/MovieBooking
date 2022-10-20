package com.example.MovieBooking.ServiceTest;


import com.example.MovieBooking.Repository.BookingRepo;
import com.example.MovieBooking.Repository.CustomerRepo;
import com.example.MovieBooking.Repository.ShowRepo;
import com.example.MovieBooking.Service.BookingService;
import com.example.MovieBooking.model.Booking;
import com.example.MovieBooking.model.Customer;
import com.example.MovieBooking.model.Show;
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
public class BookingServiceTest {

    @Mock
    private BookingRepo bookingRepo;

    @Mock
    private CustomerRepo customerRepo;

    @Mock
    private ShowRepo showRepo;

    @InjectMocks
    private BookingService bookingService;



    @Test
    public void NewBookingTesting()
    {
        Booking booking=new Booking();
        booking.setBookingTime("02:02:02");
        Customer customer=new Customer();
        customer.setId(2);
        customer.setName("anvitha");
        customer.setEmailid(("anvi@gmail.com"));
        booking.setCustomer(customer);
        Show show=new Show();
        show.setId(3);
        show.setCost(300);
        show.setStart_time("02:02:03");
        show.setEnd_time("04:04:04");
        booking.setShow(show);
        given(bookingRepo.save(ArgumentMatchers.any(Booking.class))).willReturn(booking);
        given(customerRepo.findById(2)).willReturn(Optional.of(customer));
        given(showRepo.findById(3)).willReturn(Optional.of(show));
        Booking newBooking= bookingService.addBooking(2,3,booking);
        assertThat(newBooking).isSameAs(booking);
        verify(bookingRepo).save(booking);

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
        given(bookingRepo.findAll()).willReturn(BookingList);
        List<Booking> expected= bookingService.getBooking();
        assertEquals(BookingList,expected);
        verify(bookingRepo).findAll();
    }

    @Test
    public void DeleteBookingTesting()
    {
        Booking booking=new Booking();
        booking.setId(1);
        booking.setBookingTime("02:02:02");
        when(bookingRepo.findById(booking.getId())).thenReturn(Optional.of(booking));
        bookingService.deleteBooking(booking.getId());
        verify(bookingRepo).deleteById(booking.getId());
    }

}
