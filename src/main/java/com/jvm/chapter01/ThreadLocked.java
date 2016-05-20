package com.jvm.chapter01;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by lin on 2016/3/3.
 */
public class ThreadLocked {
    /**
     * 线程死锁循环演示
     */
    public static void createBusyThread() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (true);
            }
        }, "testBusyThread");
        thread.start();
    }

    /**
     * 线程锁等待
     * @param lock
     */
    public static void createLockThread(final Object lock) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "testLockThread");
        thread.start();
    }

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //等待键盘输入
        br.readLine();
        createBusyThread();
        //等待键盘输入
        br.readLine();
        Object obj = new Object();
        createLockThread(obj);

    }


}
