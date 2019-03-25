package com.zxj.spring.aop;

public class MyBeforeAdvice extends BeforeAdvice {

    public MyBeforeAdvice(Object target) {
        super(target);
    }

    @Override
    public void beforeInvoke() {
        System.out.println("this is my before time is :" +System.currentTimeMillis());
    }
}
