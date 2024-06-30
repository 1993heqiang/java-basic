package com.example.others;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

class ConcurrencyTests {
    @Test
    void atomicTest() throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        int threadNum = 10;
        CountDownLatch latch = new CountDownLatch(threadNum);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                IntStream.range(0, 100).forEach(index -> atomicInteger.getAndIncrement());
                latch.countDown();
            }).start();
        }
        latch.await();
        Assertions.assertEquals(1000, atomicInteger.get());
    }
}
