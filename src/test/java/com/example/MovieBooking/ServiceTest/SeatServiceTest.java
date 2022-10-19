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
    private SeatRepo SeatRepo;

    @Mock
    private HallRepo HallRepo;

    @InjectMocks
    private SeatService SeatService;

//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void NewSeatTesting()
    {
        Seat Seat=new Seat();
        Seat.setNo(30);
        Hall Hall=new Hall();
        Hall.setId(2);
        Hall.setHallno(5);
        Hall.setSize(20);
        Seat.setHall(Hall);
        given(SeatRepo.save(ArgumentMatchers.any(Seat.class))).willReturn(Seat);
        given(HallRepo.findById(2)).willReturn(Optional.of(Hall));
        Seat newSeat=SeatService.addSeat(2,Seat);
        assertThat(newSeat).isSameAs(Seat);
        verify(SeatRepo).save(Seat);

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
        given(SeatRepo.findById(oldSeat.getId())).willReturn(Optional.of(oldSeat));
        SeatService.updateSeat(oldSeat.getId(),newSeat);
        verify(SeatRepo).save(newSeat);
        verify(SeatRepo).findById(oldSeat.getId());
    }


    @Test
    public void GetSeatTesting()
    {
        List<Seat> SeatList= new ArrayList<>();
        SeatList.add(new Seat());
        given(SeatRepo.findAll()).willReturn(SeatList);
        List<Seat> expected=SeatService.getSeat();
        assertEquals(SeatList,expected);
        verify(SeatRepo).findAll();
    }

    @Test
    public void DeleteSeatTesting()
    {
        Seat Seat=new Seat();
        Seat.setId(1);
        Seat.setNo(2);
        when(SeatRepo.findById(Seat.getId())).thenReturn(Optional.of(Seat));
        SeatService.deleteSeat(Seat.getId());
        verify(SeatRepo).deleteById(Seat.getId());
    }

}
