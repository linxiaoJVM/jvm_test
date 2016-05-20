package com.jvm.chapter01;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2016/3/10.
 * JDK1.6及之前版本中，常量池分配在永久代内，通过-XX:PermSize=10M -XX:MaxPermSize=10M限制方法区大小，
 * 间接限制常量池的容量
 * JDK1.6中会报错，JDK1.7的HotSpot，已经把字符串常量池移除，程序不会报错
 *
 */
public class RuntimeConstantPoolOOM {
    public static void main(String args[]) {
        interTest();

//        List<String> list = new ArrayList<String>();
//        int i = 0;
//        while (true) {
//            list.add(String.valueOf(i++).intern());
//        }
    }

    public static void interTest() {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.print(str2.intern() == str2);
    }
}
