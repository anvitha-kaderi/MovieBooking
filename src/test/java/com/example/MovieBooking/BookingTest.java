package com.example.MovieBooking;

import com.example.MovieBooking.Service.BookingService;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingTest {

    @Mock
    private BookingService bookingService;

    @Test
    public void bookingservicetesting()
    {
        int numberOfThreads = 10;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);


        for (int i = 0; i < numberOfThreads; i++) {
            service.execute(() -> {
                counter.increment();
                latch.countDown();
            });
        }
        latch.await();
        assertEquals(numberOfThreads, counter.getCount());
    }
}