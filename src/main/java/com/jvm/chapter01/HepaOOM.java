package com.jvm.chapter01;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2016/2/27.
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HepaOOM {
    static class OOPObject{

    }

    public static void main(String args[]) {
        List<OOPObject> list = new ArrayList<OOPObject>();

        while(true) {
            list.add(new OOPObject());
        }

    }

}
