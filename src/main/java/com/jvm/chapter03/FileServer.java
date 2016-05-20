package com.jvm.chapter03;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lin on 2016/5/17.
 */
public class FileServer extends Thread{
    private ServerSocket server_Socket = null;
    private ExecutorService service = null;

    public FileServer(int port) {
        try {
            server_Socket = new ServerSocket(port);
            System.out.println("socket connection successful");
            service = Executors.newFixedThreadPool(2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        while (true) {
            try {
                Socket clientSock = server_Socket.accept();
                System.out.println("new socket connection ....");
                service.execute(new SocketHandler(clientSock));
                System.out.println("SocketHandler ....");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String args[]) {
        new FileServer(4455).start();
    }
}
