package com.algebra.demo.study.lsp;

/**
 * @author al
 * @date 2020/6/4 14:17
 * @description
 */
public class BaseTest {

    public static void f(Rectangle rectangle){
        rectangle.setHeight(32);
    }

    public static void g(Rectangle rectangle){
        rectangle.setHeight(5);
        rectangle.setWidth(4);
    }


    public static void main(String[] args) {

        Rectangle r = new Square();

        f(r);
        System.out.println("f 函数："+r.getArea());

//        g(r);
//        System.out.println("g 函数："+r.getArea());

        Square s = new Square();

        f(s);
//        g(s);




    }

}
