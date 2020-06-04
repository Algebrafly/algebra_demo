package com.algebra.demo.study.lsp;

/**
 * @author al
 * @date 2020/6/4 14:15
 * @description
 */
public class Rectangle {

    private double width;
    private double height;

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getArea(){
        return width*height;
    }
}
