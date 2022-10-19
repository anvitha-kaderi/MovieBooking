//package com.example.MovieBooking;
//
//import com.example.MovieBooking.Controller.CityController;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class CityTest {
//
//
//    @InjectMocks
//    private CityController citycontroller;
//
//    @Test
//    public void Cityservicetesting()
//    {
//
//
//        int numberOfThreads = 10;
//        ExecutorService service = Executors.newFixedThreadPool(10);
//        CountDownLatch latch = new CountDownLatch(numberOfThreads);
//
//
//        for (int i = 0; i < numberOfThreads; i++) {
//            service.execute(() -> {
//                counter.increment();
//                latch.countDown();
//            });
//        }
//        latch.await();
//        assertEquals(numberOfThreads, counter.getCount());
//    }
//}