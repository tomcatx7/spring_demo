package com.zxj.spring.aop;

public class HelloServiceImp implements HelloService {
    @Override
    public void hello() {
        System.out.println("hello");
    }
}
