package com.algebra.demo.util.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author al
 * @date 2020/4/13 10:16
 * @description 实现一个容器，提供两个方法add、size，线程1添加10个元素到容器，线程2监控元素的的个数，当个数为5个时，线程2提示并结束。
 */
public class MyContainerVolatile {

    /*给list添加volatile之后，t2能够接到通知，但t2线程的死循环很浪费CPU*/

    volatile List list = new ArrayList();

    public void add(Object o){
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {

        MyContainerVolatile containerVolatile = new MyContainerVolatile();
        new Thread( () -> {
            //该线程负责往list里添加
           for (int i = 0; i < 10; i++) {
               containerVolatile.add(new Object());
               System.out.println("add-"+(i+1));
               try {
                   Thread.sleep(500);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        },"t1").start();

        new Thread( () -> {
            //该线程一直监测list的size，直到size=5
            while (true) {
                //一直监测着，很浪费CPU
                if(containerVolatile.size() == 5){
                    //此处未加同步，仍然可能会出现t1中又一次++为6了，才break
                    break;
                }
            }
            System.out.println("t2 结束");
        },"t2").start();


    }



}
