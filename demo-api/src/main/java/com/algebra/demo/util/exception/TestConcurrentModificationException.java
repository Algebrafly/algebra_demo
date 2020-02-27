package com.algebra.demo.util.exception;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author al
 * @date 2020/2/27 13:40
 * @description
 */
public class TestConcurrentModificationException {

    public static void main(String[] args) {

        ArrayList<Integer> al = new ArrayList<>();
        al.add(2);
        al.add(3);


        Iterator<Integer> iterator = al.iterator();
        // 错误方式
//
//        while(iterator.hasNext()){
//            Integer next = iterator.next();
//            if(next == 2){
//                al.remove(next);
//            }
//        }



        // 方式-1
        while(iterator.hasNext()){
            Integer next = iterator.next();
            if(next == 2){
                iterator.remove();
            }
        }


        // 方式-2
//        al.removeIf(next -> next == 2);


        System.out.println(al.toString());
    }

}
