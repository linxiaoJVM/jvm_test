package com.jvm.chapter02;

/**
 * Created by lin on 2016/3/28.
 */
public class TestThreadLocal {
    private static ThreadLocal<Obj> seqNum = new ThreadLocal<Obj>();

    public static void main(String args[]) {
        Obj obj = new Obj();
        MyThread t1 = new MyThread(obj);
        MyThread t2 = new MyThread(obj);
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();

        if(seqNum.get() == null)
            seqNum.set(obj);

        System.out.println(Thread.currentThread().getName() + " : "+seqNum.get().getRac());
    }

    static class MyThread extends Thread {
        private Obj obj;
        public MyThread(Obj obj) {
            this.obj = obj;
        }

        public void run() {
            if(seqNum.get() == null)
                seqNum.set(this.obj);

            for (int j = 0; j < 5; j++) {
                seqNum.get().increase();
                System.out.println(Thread.currentThread().getName() + " : "+seqNum.get().getRac());
            }

        }
    }
    static class Obj{
        private   int rac = 0;
        public  void increase() {
            rac ++;
        }
        public int getRac() {
            return rac;
        }

    }
}
