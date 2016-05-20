package com.jvm.chapter03;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.in;

/**
 * Created by lin on 2016/5/17.
 */
public class FileClient extends Thread{
    private Socket socket;
    private String fileName;

    public FileClient(String host, int port,String fileName) {
        try {
            socket = new Socket(host, port);
            this.fileName = fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendFile(String fileName) {
        byte [] fileSuffix = fetchFileSuffix(fileName).getBytes();

        File file = new File(fileName);
        System.out.println(fileName);
        byte[] bytes = new byte[100 * 1024];
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            OutputStream out = socket.getOutputStream();

            byte [] aa = ByteUtils.intToByteArray(fileSuffix.length);   ////前4个字节表示文件传输的后缀名称长度
            out.write(aa, 0, aa.length);

            out.write(fileSuffix, 0, fileSuffix.length); //前 length 个字节表示文件传输的后缀名称

            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
            }

            out.close();
            in.close();
            socket.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String fetchFileSuffix(String fileName) {
        if(fileName == null) return "";

        int n = fileName.lastIndexOf(File.separator);
        String temp = fileName.substring(n+1);
        System.out.println(temp);
        n = temp.indexOf(".");
        System.out.println(temp.substring(n+1));
        return fileName.substring(n+1);
    }
    public void run() {
        sendFile(this.fileName);
    }

    public static void main(String[] args) throws IOException {
        String str  = new FileClient("127.0.0.1", 4455, "E:\\MRY.TS1280清晰国语中英双字.mp4")
                .fetchFileSuffix("E:\\MRY.TS1280清晰国语中英双字.mp4");
        System.out.println(str);
//        new FileClient("127.0.0.1", 4455, "E:\\MRY.TS1280清晰国语中英双字.mp4").start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        new FileClient("127.0.0.1", 4455, "E:\\hadoop.rar").start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        new FileClient("127.0.0.1", 4455, "F:\\518000004\\2016-03-26.tar.gz").start();

    }




}
