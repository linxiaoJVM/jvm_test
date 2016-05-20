package com.jvm.chapter01;

/**
 * Created by lin on 2016/3/11.
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails
 */
public class TestAllocation {
    private static final int _1mb = 1024*1024;
    public static void main(String args[]) {
        byte[] a1,a2,a3,a4,a5;
        a1 = new byte[ 2 * _1mb];
        a2 = new byte[ 2 * _1mb];
        a3 = new byte[ 2 * _1mb];
        a4 = new byte[ 4 * _1mb];
        a5 = new byte[ 4 * _1mb];
        try {
            Thread.sleep(600*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
