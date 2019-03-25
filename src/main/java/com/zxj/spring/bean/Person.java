package com.zxj.spring.bean;

public class Person {
    private String name;
    private Eye eye;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Eye getEye() {
        return eye;
    }

    public void setEye(Eye eye) {
        this.eye = eye;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", eye=" + eye +
                '}';
    }
}
