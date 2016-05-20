package com.jvm.chapter04.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * Created by lin on 2016/5/19.
 */
public class Client {
    private Socket socket;

    public Client(String host, int port) {
        try {
            socket = new Socket(host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  <T> T refer(final Class<T> interfaceClass) {
        if (interfaceClass == null)
            throw new IllegalArgumentException("Interface class == null");
        System.out.println("Get remote service " + interfaceClass.getName() );
//        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[] {interfaceClass},new A());
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[] {interfaceClass}, new InvocationHandler() {

            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                ObjectOutputStream output = null;
                ObjectInputStream input = null;
                Object result = null;
                try {
                    output = new ObjectOutputStream(socket.getOutputStream());

                    System.out.println(method.getName());
                    output.writeUTF(method.getName());
                    output.writeObject(method.getParameterTypes());
                    output.writeObject(args);

                    input = new ObjectInputStream(socket.getInputStream());
                    result = input.readObject();
                    if (result instanceof Throwable) {
                        throw (Throwable) result;
                    }


                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.out.println("Can't get socket input stream. ");
                }catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    output.close();
                    input.close();
                    socket.close();
                }
                return result;
            }
        });

    }
    final   class A implements InvocationHandler {

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            ObjectOutputStream output = null;
            ObjectInputStream input = null;
            Object result = null;
            try {
                output = new ObjectOutputStream(socket.getOutputStream());

                System.out.println(method.getName());
                output.writeUTF(method.getName());
                output.writeObject(method.getParameterTypes());
                output.writeObject(args);

                input = new ObjectInputStream(socket.getInputStream());
                result = input.readObject();
                if (result instanceof Throwable) {
                    throw (Throwable) result;
                }


            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Can't get socket input stream. ");
            }catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                output.close();
                input.close();
                socket.close();
            }
            return result;
        }
    }


}
