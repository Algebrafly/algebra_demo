package com.algebra.demo.util.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author al
 * @date 2020/4/15 11:48
 * @description 模拟斗地主时候，只有三个玩家全部准备好才可以发牌
 */
public class CountDownLatchTest extends Thread {

    private static int count = 1;
    private final int id = count++;
    private CountDownLatch latch;

    public CountDownLatchTest (CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("[玩家:"+id+"] 已准备！");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(3);
        System.out.println("牌局开始，请准备...");
        new CountDownLatchTest(latch).start();
        new CountDownLatchTest(latch).start();
        new CountDownLatchTest(latch).start();
        latch.await();

        System.out.println("玩家已到齐，开始游戏！");

    }

}
