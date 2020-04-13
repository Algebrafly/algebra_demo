package com.algebra.demo.util.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author al
 * @date 2020/4/13 9:59
 * @description volatile关键字不具备原子性
 */
public class VolatileNoAtomic {

    /*10个线程分别执行10000次count++，count是对象vna的成员变量，按理来说最终count=10000，
      但是最终每次执行结果都不一样，count一直小于10000，说明volatile不具备原子性*/

    volatile int count = 0;

    void m () {
        for(int i = 0; i < 1000; i++){
            count++;
        }
    }

    public static void main(String[] args) {
        VolatileNoAtomic vt = new VolatileNoAtomic();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(vt::m,"t_"+i));
        }

        threads.forEach(o -> o.start());
        threads.forEach((o) -> {
            try {
                // join()方法阻塞调用此方法的线程,直到线程t完成，此线程再继续。通常用于在main()主线程内，等待其它线程完成再结束main()主线程。
                // 相当于在main线程中同步o线程，o执行完了，main线程才有执行的机会
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        System.out.println(vt.count);
    }


}
