package com.example.MovieBooking.ServiceTest;


import com.example.MovieBooking.Repository.SeatRepo;
import com.example.MovieBooking.Repository.HallRepo;
import com.example.MovieBooking.Service.SeatService;
import com.example.MovieBooking.model.Seat;
import com.example.MovieBooking.model.Hall;
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
public class SeatServiceTest {

    @Mock
    private SeatRepo seatRepo;

    @Mock
    private HallRepo hallRepo;

    @InjectMocks
    private SeatService seatService;

//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void NewSeatTesting()
    {
        Seat seat=new Seat();
        seat.setNo(30);
        Hall Hall=new Hall();
        Hall.setId(2);
        Hall.setHallno(5);
        Hall.setSize(20);
        seat.setHall(Hall);
        given(seatRepo.save(ArgumentMatchers.any(Seat.class))).willReturn(seat);
        given(hallRepo.findById(2)).willReturn(Optional.of(Hall));
        Seat newSeat= seatService.addSeat(2,seat);
        assertThat(newSeat).isSameAs(seat);
        verify(seatRepo).save(seat);

    }

    @Test
    public void UpdateSeatTesting()
    {
        Seat oldSeat=new Seat();
        oldSeat.setId(1);
        oldSeat.setNo(5);
        Seat newSeat=new Seat();
        newSeat.setId(1);
        newSeat.setNo(2);
        given(seatRepo.findById(oldSeat.getId())).willReturn(Optional.of(oldSeat));
        seatService.updateSeat(oldSeat.getId(),newSeat);
        verify(seatRepo).save(newSeat);
        verify(seatRepo).findById(oldSeat.getId());
    }


    @Test
    public void GetSeatTesting()
    {
        List<Seat> SeatList= new ArrayList<>();
        SeatList.add(new Seat());
        given(seatRepo.findAll()).willReturn(SeatList);
        List<Seat> expected= seatService.getSeat();
        assertEquals(SeatList,expected);
        verify(seatRepo).findAll();
    }

    @Test
    public void DeleteSeatTesting()
    {
        Seat seat=new Seat();
        seat.setId(1);
        seat.setNo(2);
        when(seatRepo.findById(seat.getId())).thenReturn(Optional.of(seat));
        seatService.deleteSeat(seat.getId());
        verify(seatRepo).deleteById(seat.getId());
    }

}
