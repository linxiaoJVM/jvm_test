package com.jvm.chapter04;

import com.jvm.chapter04.service.HelloService;

/**
 * Created by lin on 2016/5/19.
 */
public class RpcProvider {
    public static void main(String args[]) {
        HelloService service = new HelloServiceImpl();
        RpcFramework rpcFramework = new RpcFramework(2233);
        //暴露接口，对外提供服务
        rpcFramework.export(service);
    }
}
