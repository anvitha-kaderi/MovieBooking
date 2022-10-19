//package com.example.MovieBooking;
//
//public class thread {
//
//        public static void main(String[] args) throws Exception {
//
//            ThreadPool threadPool = new ThreadPool(3, 10);
//
//            for(int i=0; i<10; i++) {
//
//                int taskNo = i;
//                threadPool.execute( () -> {
//                    String message =
//                            Thread.currentThread().getName()
//                                    + ": Task " + taskNo ;
//                    System.out.println(message);
//                });
//            }
//
//            threadPool.waitUntilAllTasksFinished();
//            threadPool.stop();
//
//        }
//    }

//int numberOfThreads = 10;
//        ExecutorService service = Executors.newFixedThreadPool(10);
//        CountDownLatch latch = new CountDownLatch(numberOfThreads);
//
//
//        for (int i = 0; i < numberOfThreads; i++) {
//        service.execute(() -> {
//        counter.increment();
//        latch.countDown();
//        });
//        }
//        latch.await();
//        assertEquals(numberOfThreads, counter.getCount());
