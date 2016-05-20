package com.jvm.chapter01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lin on 2016/3/9.
 */
public class AtomicTest {
    static AtomicInteger race = new AtomicInteger(0);
    public static void increase() {
        race.incrementAndGet();
    }

    private static final int THREAD_COUNT = 20;
    private static Thread [] threads = new Thread[THREAD_COUNT];

    public static void main(String args[]) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }
        System.out.println(Thread.activeCount());

        for (int i = 0; i < THREAD_COUNT; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.activeCount());

        System.out.println(race);
    }


}
