package com.algebra.demo.study.lsp;

/**
 * @author al
 * @date 2020/6/4 16:24
 * @description 直线
 */
public class Line extends LinerObject {

    public Line(){}

    public Line(double slope, double intercept){
        super.setSlope(slope);
        super.setIntercept(intercept);
    }

    @Override
    boolean isOn(Point point) {
        double y = point.getyPosition();
        double x = point.getxPosition();
        double result = super.getSlope()*x + super.getIntercept();
        return Double.valueOf(y).equals(result);
    }
}
