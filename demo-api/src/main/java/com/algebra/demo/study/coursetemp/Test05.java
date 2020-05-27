package com.algebra.demo.study.coursetemp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author al
 * @date 2020/5/26 17:23
 * @description 循环体内，字符串的连接方式，使用 StringBuilder 的 append 方法进行扩展。
 */
public class Test05 {

    public static void test(){

        List<String> al = new ArrayList<>();
        al.add("asd");
        al.add("dfg");
        al.add("123");
        al.add("456");
        al.add("99aa");
        System.out.println("原集合："+al.toString());

        List<String> subList = al.subList(1, 3);
        System.out.println("子集合："+subList.toString());

        ArrayList<String> another = (ArrayList<String>) subList;
        System.out.println(another.toString());
    }


    public static void main(String[] args) {

        test();

    }

}
