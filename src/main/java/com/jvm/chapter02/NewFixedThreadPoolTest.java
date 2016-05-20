package com.jvm.chapter02;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.*;

/**
 * Created by lin on 2016/3/22.
 */
public class NewFixedThreadPoolTest {

    public static void main(String args[]) throws IOException,InterruptedException{
        selfDefThreadPool();
    }

    /**
     * 固定大小线程池
     */
    public static void newFixedThreadPool() {
        //创建一个可重用固定线程数的线程池
        ExecutorService service = Executors.newFixedThreadPool(2);
        for(int i = 0; i < 4; i++) {
            Runnable run = new Runnable() {
                public void run() {
                    for(int j = 0; j < 10; j++) {
                        System.out.println(Thread.currentThread().getName()+", "+j);
                    }
                }
            };
            service.execute(run);
        }
        service.shutdown();
        System.out.println("all thread complete");
    }

    /**
     * 创建单个线程
     */
    public static void newSingleThreadExecutor() {
        //创建单个线程
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 4; i++) {
            Runnable run = new Runnable() {
                public void run() {
                    for(int j = 0; j < 10; j++) {
                        System.out.println(Thread.currentThread().getName()+", "+j);
                    }
                }
            };

            service.execute(run);
        }

        service.shutdown();
        System.out.println("all thread complete");
    }

    /**
     * 动态创建线程池大小
     */
    public static void newCachedThreadPool() {
        //创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们。
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 4; i++) {
            Runnable run = new Runnable() {
                public void run() {
                    for(int j = 0; j < 10; j++) {
                        System.out.println(Thread.currentThread().getName()+", "+j);
                    }
                }
            };

            service.execute(run);
        }

        service.shutdown();
        System.out.println("all thread complete");
    }

    /**
     * 创建定时线程池
     */
    public static void newScheduledThreadPool() {
        //创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行
        ScheduledExecutorService  service = Executors.newScheduledThreadPool(2);
        for (int i = 0; i < 1; i++) {
            Runnable run = new Runnable() {
                public void run() {
                    for(int j = 0; j < 5; j++) {
                        System.out.println(Thread.currentThread().getName()+", "+j);
                    }
                }
            };

//            service.schedule(run, i*2000, TimeUnit.MILLISECONDS);
            service.scheduleAtFixedRate(run, i*2000, 10000, TimeUnit.MILLISECONDS);
        }

//        service.shutdown();
        System.out.println("all thread complete");
    }

    /**
     * 单线程定时线程
     */
    public static void newSingleThreadScheduledExecutor() {
        //创建一个线程池，单线程定时线程
        ScheduledExecutorService  service = Executors.newSingleThreadScheduledExecutor();
        for (int i = 0; i < 2; i++) {
            Runnable run = new Runnable() {
                public void run() {
                    for(int j = 0; j < 5; j++) {
                        System.out.println(Thread.currentThread().getName()+", "+j);
                    }
                }
            };

            service.schedule(run, i*2000, TimeUnit.MILLISECONDS);
//            service.scheduleAtFixedRate(run, i*2000, 10000, TimeUnit.MILLISECONDS);
        }

//        service.shutdown();
        System.out.println("all thread complete");
    }

    /**
     * 自定义线程池
     */
    public static void selfDefThreadPool() {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 5, 10000, TimeUnit.MILLISECONDS, queue);
        for (int i = 0; i < 15; i++) {
            Runnable run = new Runnable() {
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        System.out.println(Thread.currentThread().getName() + ", " + j);
                    }
                }
            };
            pool.execute(run);
        }

        pool.shutdown();
        System.out.println("all thread complete");
    }
}
