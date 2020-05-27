package com.algebra.demo.study.coursetemp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author al
 * @date 2020/5/26 17:23
 * @description ConcurrentModificationException
 */
public class Test08 {

    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args)  {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Thread thread1 = new Thread(){
            @Override
            public void run() {
//                synchronized (Test08.class) {
                    Iterator<Integer> iterator = list.iterator();
                    while(iterator.hasNext()){
                        Integer integer = iterator.next();
                        System.out.println(integer);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
//                }

            };
        };
        Thread thread2 = new Thread(){
            @Override
            public void run() {
//                synchronized (Test08.class) {
                    Iterator<Integer> iterator = list.iterator();
                    while(iterator.hasNext()){
                        Integer integer = iterator.next();
                        if(integer==2) {
                            iterator.remove();
                        }
                    }
//                }

            };
        };
        thread1.start();
        thread2.start();
    }

}
