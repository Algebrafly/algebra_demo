package com.algebra.demo.study.lsp;

/**
 * @author al
 * @date 2020/6/4 16:17
 * @description
 */
public abstract class LinerObject {

    private double slope;

    private double intercept;

    public double getIntercept() {
        return intercept;
    }

    public double getSlope() {
        return slope;
    }

    public void setSlope(double slope) {
        this.slope = slope;
    }

    public void setIntercept(double intercept) {
        this.intercept = intercept;
    }

    /**
     * 判断一个点是否在直线上
     * @param point 点对象
     * @return boolean
     */
    abstract boolean isOn(Point point);


}
