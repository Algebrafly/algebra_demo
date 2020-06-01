package com.algebra.demo.study.ocp;

/**
 * @author al
 * @date 2020/6/1 16:41
 * @description
 */
public class Square implements Shape, Comparable<Square> {

    private String name;

    private Integer type;

    public Square(){}

    public Square(String name, Integer type){
        this.name = name;
        this.type = type;
    }

    @Override
    public void draw() {
        System.out.println("Square : "+this.name+" draw!");
    }

    @Override
    public Integer getType() {
        return type;
    }

    @Override
    public int compareTo(Square o) {
        return 0;
    }
}
