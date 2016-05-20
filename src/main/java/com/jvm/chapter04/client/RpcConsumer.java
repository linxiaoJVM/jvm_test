package com.jvm.chapter04.client;

import com.jvm.chapter04.service.HelloService;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * Created by lin on 2016/5/19.
 */
public class RpcConsumer {
    public static void main(String args[]) {
        Client client = new Client("127.0.0.1", 2233);
        HelloService helloService = client.refer(HelloService.class);
        System.out.println("helloService " + helloService.getClass().getName() );
        System.out.println(helloService.sayHello("world "));
        System.out.println(helloService.sayHello("lin "));
        createProxyClassFile();
//        for (int i = 0; i < 10; i ++) {
//            System.out.println(helloService.sayHello("world "+i));
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }

    public static void createProxyClassFile() {
        String name = "ProxyHello";
        byte[] data = ProxyGenerator.generateProxyClass( name, new Class[] { HelloService.class } );
        try
        {
            FileOutputStream out = new FileOutputStream( name + ".class" );
            out.write( data );
            out.close();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}
