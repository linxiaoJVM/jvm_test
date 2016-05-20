package com.jvm.chapter02;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * Created by lin on 2016/3/25.
 */
public class CountTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 100;
    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if(this.end - this.start <= THRESHOLD) {
            for (int i = this.start; i <= this.end; i++)
                sum += i;
        } else {
            int middle = (this.start + this.end) / 2;
            CountTask leftTask = new CountTask(this.start, middle);
            CountTask rightTask = new CountTask(middle+1, this.end);
            leftTask.fork();
            rightTask.fork();
            sum = leftTask.join() + rightTask.join();
        }
        return sum;
    }
    public static void main(String args[]) {
        long starttime = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Integer> task = forkJoinPool.submit(new CountTask(1, 10000));
        try {
            System.out.println(task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("fork/join "+ (System.currentTimeMillis() - starttime));

    }
}
