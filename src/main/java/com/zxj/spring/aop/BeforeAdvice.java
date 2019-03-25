package com.zxj.spring.aop;

import java.lang.reflect.Method;

public abstract class  BeforeAdvice implements Advice {
    private Object target;

    public BeforeAdvice(Object target) {
        this.target = target;
    }

    abstract public void beforeInvoke();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        beforeInvoke();
        return method.invoke(target,args);
    }
}
