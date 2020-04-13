package com.algebra.demo.util.concurrent;

/**
 * @author al
 * @date 2020/4/13 9:44
 * @description volatile 可见性测试
 */
public class VolatileVisible {

    volatile boolean running = true;

    void m(){
        System.out.println("m start...");
        int count = 0;
        while (running) {
            // 死循环，只有running = false 时候，才能执行后面语句
            count++;
            System.out.println("我被执行了"+count+"次");
        }
        System.out.println("m end...");
    }

    public static void main(String[] args) {
        VolatileVisible vt = new VolatileVisible();
        new Thread(vt::m,"t1").start();

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 如果running变量没被volatile修饰，线程就看不见running被修改了
        vt.running = false;

    }






}
