package com.algebra.demo.util.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author al
 * @date 2020/4/13 10:16
 * @description 实现一个容器，提供两个方法add、size，线程1添加10个元素到容器，线程2监控元素的的个数，当个数为5个时，线程2提示并结束。
 *
 */
public class MyContainerWaitNotify {

    /*wait会释放锁，notify则不会。t1中notify唤醒t2，本线程不会释放锁，会一直执行下去直至被wait或synchronized代码块结束*/

    volatile List list = new ArrayList();

    public void add(Object o){
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {

        MyContainerWaitNotify containerVolatile = new MyContainerWaitNotify();

        final Object lock = new Object();

        new Thread( () -> {
            synchronized (lock) {
                System.out.println("*** 线程T2 启动 ***");
                if(containerVolatile.size() != 5){
                    try {
                        //size不等于5时，就一直在那等着，直到被t1叫醒
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("*** 线程T2 结束 ***");
                //通知t1继续执行
                lock.notify();
            }
        },"t2").start();

        new Thread( () -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    containerVolatile.add(new Object());
                    System.out.println("add-"+(i+1));
                    if(containerVolatile.size() == 5){
                        lock.notify(); //唤醒另一个线程t2，本线程继续执行，直至synchronized包裹的代码块结束或者调用了wait
                        try {
                            //释放锁，让t2得以执行
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        },"t1").start();

    }



}
