package com.jvm.chapter02;

import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lin on 2016/3/24.
 */
public class Outer {
    private String a;


    class Inner {
        private  int i;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }

    public void test() {
        Inner in = new Inner();

    }
    public static void main(String args[]) {

//        Outer.Inner in = new Outer().new Inner();
//        ClassLoader cl;
//        Hashtable tabkle;
//        ConcurrentHashMap cgml;
//        System.load("");
//        String a = new String("dd");

        int a = 100, b = 100;
        System.out.println(a == b); // true，缓存了
        Integer c = 1000, d = 1000;
        System.out.println(c == d); // false，没有缓存
        Integer e = -128, f = -128;
        System.out.println(e == f); // true，缓存了
        Integer g = -129, h = -129;
        System.out.println(g == h); // false，没有缓存


        String str1 = new String("123");
        String str2 = "123";

        String str3 = str1.intern();

        System.out.println((str1 == str2) +","+ (str3 == str2));
        //输出 false,true

        String str4 = new String("234");
        String str5 = new String("234");

        String str6 = str4.intern();
        String str7 = str5.intern();

        //输出 false,true
        System.out.println((str4 == str5) +","+ (str6 == str7));
    }
}
