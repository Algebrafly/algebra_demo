package com.algebra.demo.study.ocp;

/**
 * @author al
 * @date 2020/6/1 16:41
 * @description
 */
public class Circle implements Shape, Comparable<Circle> {

    private String name;

    private Integer type;

    public Circle(){}

    public Circle(String name, Integer type){
        this.name = name;
        this.type = type;
    }

    @Override
    public void draw() {
        System.out.println("Circle : "+this.name+" draw!");
    }

    @Override
    public Integer getType() {
        return type;
    }

    @Override
    public int compareTo(Circle o) {
        return 0;
    }
}
