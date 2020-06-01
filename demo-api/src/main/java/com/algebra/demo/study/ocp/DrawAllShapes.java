package com.algebra.demo.study.ocp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author al
 * @date 2020/6/1 16:42
 * @description
 */
public class DrawAllShapes {

    public void drawShapes(List<Shape> shapes) {
        for (Shape shape : shapes) {
            shape.draw();
        }
    }

    public static void main(String[] args) {

        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle("A",1));
        shapes.add(new Square("A",2));
        shapes.add(new Square("B",2));
        shapes.add(new Square("C",2));
        shapes.add(new Circle("B",1));

        // 按照指定优先级排序
        shapes.sort(new ShapeComparer());

        new DrawAllShapes().drawShapes(shapes);

    }

}
