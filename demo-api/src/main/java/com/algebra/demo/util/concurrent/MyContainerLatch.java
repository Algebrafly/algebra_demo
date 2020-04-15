package com.algebra.demo.util.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author al
 * @date 2020/4/13 10:16
 * @description 实现一个容器，提供两个方法add、size，线程1添加10个元素到容器，线程2监控元素的的个数，当个数为5个时，线程2提示并结束。
 */
public class MyContainerLatch {

    /*给list添加volatile之后，t2能够接到通知，但t2线程的死循环很浪费CPU*/

    volatile List list = new ArrayList();

    public void add(Object o){
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {

        MyContainerLatch containerVolatile = new MyContainerLatch();
        CountDownLatch latch = new CountDownLatch(1);

        new Thread( () -> {
            System.out.println("*** T2启动 ***");
            if(containerVolatile.size() != 5) {
                try {
                    // 等待不需要锁定一个对象
                    latch.await();
                    //latch.await(5000,TimeUnit.MILLISECONDS); //也可以指定等待时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("*** T2结束 ***");
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread( () -> {
            System.out.println("*** T1启动 ***");
            for (int i = 0; i < 10; i++) {
               containerVolatile.add(new Object());
               System.out.println("add-"+(i+1));
               if(containerVolatile.size() == 5){
                   //打开门闩，让t2得以执行。调用一次countDown，就减1
                   latch.countDown();
               }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("*** T1结束 ***");
        },"t1").start();




    }



}
