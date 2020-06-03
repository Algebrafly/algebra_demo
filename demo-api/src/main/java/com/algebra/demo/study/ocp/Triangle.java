package com.algebra.demo.study.ocp;

/**
 * @author al
 * @date 2020/6/3 13:49
 * @description
 */
public class Triangle implements Shape {

    private String name;

    private Integer type;

    public Triangle(){}

    public Triangle(String name, Integer type){
        this.name = name;
        this.type = type;
    }

    @Override
    public void draw() {
        System.out.println("Triangle : "+this.name+" draw!");
    }

    @Override
    public Integer getType() {
        return type;
    }
}
