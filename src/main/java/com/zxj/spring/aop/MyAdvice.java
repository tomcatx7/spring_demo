package com.zxj.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyAdvice implements InvocationHandler {
    private Object target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
