package com.algebra.demo.study.lsp;

/**
 * @author al
 * @date 2020/6/4 16:22
 * @description
 */
public class Point {

    private double xPosition;

    private double yPosition;

    public Point(){}

    public Point(double xPosition, double yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    public double getxPosition() {
        return xPosition;
    }

    public double getyPosition() {
        return yPosition;
    }
}
