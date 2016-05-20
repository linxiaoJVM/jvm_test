package com.jvm.chapter03;

import java.io.*;
import java.net.Socket;

/**
 * Created by lin on 2016/5/18.
 */
public class SocketHandler implements Runnable {
    private Socket socket;
    public SocketHandler(Socket socket){this.socket = socket;}

    public void run() {
        System.out.println(Thread.currentThread().getName()+", begin");
        saveFile(socket);
        System.out.println(Thread.currentThread().getName()+", end");
    }

    private  void saveFile(Socket socket) {
        InputStream in = null;
        OutputStream out = null;

        try {
            in = socket.getInputStream();
        } catch (IOException ex) {
            System.out.println("Can't get socket input stream. ");
        }
        byte[] bytes = new byte[100*1024];

        try {
            in.read(bytes, 0, 4);                           //读取前4个字节，提取出文件后缀名称长度
            int fileSuffixLength = ByteUtils.byteArrayToInt(bytes);

            in.read(bytes, 4, fileSuffixLength);            //文件后缀名称
            String fileSuffix = new String(bytes, 4, fileSuffixLength);    //转化成字符串

            System.out.println("fileSuffix "+fileSuffix);
            String file = "d:\\up\\"+System.currentTimeMillis()+".tar."+fileSuffix;

            out = new BufferedOutputStream(new FileOutputStream(file));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found. ");
        }catch (IOException e) {
            e.printStackTrace();
        }



        int count;
        try {
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
                //System.out.println("reciver: "+count);
            }
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
