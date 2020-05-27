package com.algebra.demo.study.coursetemp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author al
 * @date 2020/5/26 17:23
 * @description ConcurrentModificationException
 */
public class Test07 {

    public static void testSubList(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        List<Integer> subList = list.subList(1, 3);
        list.add(4);
        System.out.println(subList);
    }

    public static void testIteratorError(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            Integer integer = iterator.next();
            if(integer==2) {
                list.remove(integer);
            }
        }
    }

    public static void testIteratorError2(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.forEach(d->{
            if(d.equals(2)){
                list.remove(2);
            }
        });
    }

    public static void testIteratorCorrect(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            Integer integer = iterator.next();
            if(integer==2) {
                iterator.remove();
            }
        }
    }

    public static void testIteratorCorrect2(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(2);
        list.removeIf(integer -> integer == 2);
    }



    public static void main(String[] args) {

        testIteratorCorrect2();

    }

}
