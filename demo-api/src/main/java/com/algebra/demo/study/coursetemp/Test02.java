package com.algebra.demo.study.coursetemp;

/**
 * @author al
 * @date 2020/5/26 17:23
 * @description 所有整型包装类对象之间值的比较，全部使用 equals 方法比较。
 */
public class Test02 {

    public static void main(String[] args) {

        Integer a = 125;
        Integer b = 125;

        Integer c = 996;
        Integer d = 996;

        System.out.println(a == b);
        System.out.println(a.equals(b));

        System.out.println(c == d); // false
        System.out.println(c.equals(d));

    }

}
