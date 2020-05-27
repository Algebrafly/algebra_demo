package com.algebra.demo.study.coursetemp;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author al
 * @date 2020/5/26 17:23
 * @description ConcurrentModificationException
 */
public class Test09 {


    static List<Integer> list = new CopyOnWriteArrayList<>();

    public static void main(String[] args)  {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Thread thread1 = new Thread(() -> {
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

        });
        Thread thread2 = new Thread(() -> list.removeIf(d->d.equals(2)));
        thread1.start();
        thread2.start();
    }

}
