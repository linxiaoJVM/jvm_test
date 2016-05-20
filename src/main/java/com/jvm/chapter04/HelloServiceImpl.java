package com.jvm.chapter04;

import com.jvm.chapter04.service.HelloService;

/**
 * Created by lin on 2016/5/19.
 */
public class HelloServiceImpl implements HelloService {
    public String sayHello(String name) {
        return "hello " + name;
    }

    public String sayGoodBy(String name) {
        return "goodby " + name;
    }
}
