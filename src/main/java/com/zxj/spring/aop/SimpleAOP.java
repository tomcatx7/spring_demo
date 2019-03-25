package com.zxj.spring.aop;

import java.lang.reflect.Proxy;

public class SimpleAOP {

    public static Object getProxy(Object target, Advice myAdvice){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),myAdvice);
    }

}
