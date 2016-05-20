package com.jvm.chapter01;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2016/3/3.
 */
public class OOMObject {

    static class OOMobject {
        public byte [] placeholder = new byte[64*1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMobject> list = new ArrayList<OOMobject>();
        for(int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMobject());
        }
        System.gc();
    }
    public static void main(String args[]) throws Exception{
        Thread.sleep(60000);
        fillHeap(1000);
    }
}
