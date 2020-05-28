package com.algebra.demo.study.coursetemp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author al
 * @date 2020/5/26 17:23
 * @description ConcurrentModificationException
 */
public class Test10 {

    public static void testPlate(){

        // error: incompatible types: Plate<Apple> cannot be converted to Plate<Fruit>
//        Plate<Fruit> p = new Plate<Apple>(new Apple());

        // 一个能放水果以及一切是水果派生类的盘子。ps.啥水果都能放的盘子。
        Plate<? extends Fruit> p = new Plate<>(new Apple("aaa",new Date()));
        Fruit item = p.getItem();
//        p.setItem(new Orange());
//        p.setItem(new Fruit());
        System.out.println(item);


        // 一个能放水果以及一切是水果基类的盘子。
        Plate<? super Fruit> p2 = new Plate<>(new Food());
        Object item1 = p2.getItem();
        System.out.println(item1);
        p2.setItem(new Apple("bbb",new Date()));

    }


    public static void test01(){
        //
        List<? extends Fruit> al = new ArrayList<>();



    }

    public static void test02(){

        List<? super Fruit> al = new ArrayList<>();


    }


    public static void main(String[] args)  {
        testPlate();





    }

}
