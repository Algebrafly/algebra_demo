package com.algebra.demo.study.lsp;

/**
 * @author al
 * @date 2020/6/4 16:28
 * @description 线段
 */
public class LineSegment extends LinerObject {

    private Point startPoint;

    private Point endPoint;

    public LineSegment(){}

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    /**
     * 获取线段长度
     * @return 长度
     */
    public double getLength(){
        double xStart = startPoint.getxPosition();
        double yStart = startPoint.getyPosition();

        double xEnd = endPoint.getxPosition();
        double yEnd = endPoint.getyPosition();

        return Math.sqrt(Math.pow((xStart-xEnd),2)+Math.pow((yStart-yEnd),2));
    }


    /**
     * （退化函数）
     * @param point 点对象
     * @return
     */
    @Override
    boolean isOn(Point point) {
        return false;
    }
}
