package com.zxj.spring.bean;

public class Eye {
    private String num;
    private String color;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Eye{" +
                "num=" + num +
                ", color='" + color + '\'' +
                '}';
    }
}
