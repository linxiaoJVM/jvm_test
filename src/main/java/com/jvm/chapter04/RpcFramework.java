package com.jvm.chapter04;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lin on 2016/5/19.
 */
public class RpcFramework {
    private ServerSocket server_Socket = null;
    private ExecutorService service = null;

    public RpcFramework(int port) {
        if (port <= 0 || port > 65535)
            throw new IllegalArgumentException("Invalid port " + port);

        try {
            server_Socket = new ServerSocket(port);
            System.out.println("socket connection successful");
            service = Executors.newFixedThreadPool(2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void export(final Object obj) {
        if (obj == null)
            throw new IllegalArgumentException("service instance == null");
        System.out.println("Export service " + obj.getClass().getName());

        for(;;) {
            System.out.println("Waiting...");
            try {
                Socket clientSock = server_Socket.accept();
                System.out.println("Accepted connection : " + clientSock);
                service.execute(new SocketHandler(clientSock, obj));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
