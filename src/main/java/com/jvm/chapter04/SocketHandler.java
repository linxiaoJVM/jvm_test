package com.jvm.chapter04;

import com.jvm.chapter03.ByteUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Created by lin on 2016/5/18.
 */
public class SocketHandler implements Runnable {
    private Socket socket;
    private Object obj;
    public SocketHandler(Socket socket, final Object obj){this.socket = socket;this.obj=obj;}

    public void run() {
        System.out.println(Thread.currentThread().getName()+", begin call ");
        call(socket, obj);
        System.out.println(Thread.currentThread().getName()+", end call");
    }

    private  void call(Socket socket,Object obj) {
        ObjectOutput output = null;
        ObjectInput input = null;
        try {
            input = new ObjectInputStream(socket.getInputStream());

            String methodName = input.readUTF();                           //方法名字
            Class<?>[] parameterTypes = (Class<?>[])input.readObject();    //方法参数类型
            Object[] arguments = (Object[])input.readObject();             //方法参数

            System.out.println("methodName "+methodName);
            try {
                Method method = obj.getClass().getMethod(methodName, parameterTypes);   //找出要调用的方法
                Object result = method.invoke(obj, arguments);      //调用方法，返回结果

                output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(result);     //结果返回给客户端

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            input.close();
            output.close();
            socket.close();

        } catch (IOException ex) {
            System.out.println("Can't get socket input stream. ");
            ex.printStackTrace();
        }catch (ClassNotFoundException ex) {
            System.out.println("Can't get socket input stream. ");
        }
    }
}
