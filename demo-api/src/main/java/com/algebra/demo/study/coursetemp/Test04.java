package com.algebra.demo.study.coursetemp;

/**
 * @author al
 * @date 2020/5/26 17:23
 * @description 循环体内，字符串的连接方式，使用 StringBuilder 的 append 方法进行扩展。
 */
public class Test04 {

    public static void testPlus(){
        String str = "aa";
        for (int i = 0; i < 100; i++) {
            str = str + ":" + i;
        }
        System.out.println(str);
    }

    public static void testAppender(){
        String str = "aa";
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < 100; i++) {
            sb.append(":").append(i);
        }
        System.out.println(sb.toString());
    }


    public static void main(String[] args) {

        testAppender();

    }

}
