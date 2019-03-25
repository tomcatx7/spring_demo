package com.zxj.spring.aop;

public class AopTest {
    public static void main(String[] args) {
        HelloServiceImp helloServiceImp = new HelloServiceImp();
        MyBeforeAdvice beforeAdvice = new MyBeforeAdvice(helloServiceImp);
        HelloService proxy = (HelloService)SimpleAOP.getProxy(helloServiceImp, beforeAdvice);
        proxy.hello();
    }
}
