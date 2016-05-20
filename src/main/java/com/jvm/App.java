package com.jvm;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Integer a = 10;
        Integer b = 10;
        System.out.println(a == b);

        HashMap hl;
        ConcurrentHashMap chm;
        CopyOnWriteArrayList coal;
        TreeMap t;
        List li;
        Number num[] = new Integer[2];
        num[0] = 25;
        Integer c = 1000;

        Integer d = 135;
        int one =  Integer.highestOneBit(d );
        System.out.println(one +" "+ (one << 1) );

        System.out.println(c == d);

        System.out.println( "Hello World!" );

//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++) {
//            int code = 311/16;
//        }
//        System.out.println(System.currentTimeMillis()-start);
//
//        start = System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++) {
//            int code = 311&(16-1);
//        }
//        System.out.println(System.currentTimeMillis()-start);

    }
}
