package com.algebra.demo.util.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author al
 * @date 2020/4/15 14:20
 * @description
 */
public class CyclicBarrierTest implements Runnable {

    private static final int FINISH_LINE = 75;
    private static List<Horse> horses = new ArrayList<>();
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public void run() {
        StringBuilder sb = new StringBuilder();
        // 打印赛道
        for (int i = 0; i < FINISH_LINE; i++) {
            sb.append("=");
        }
        System.out.println(sb);

        // 打印赛马轨迹
        for (Horse horse : horses) {
            System.out.println(horse.tracks());
        }

        // 判断是否结束
        for (Horse horse : horses) {
            if(horse.getStrides() >= FINISH_LINE){
                System.out.println(horse + "win!");
                executorService.shutdownNow();
                return;
            }
        }

        // 休息指定时间到下一轮
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("barrier-action sleep interrupted");
        }

    }


    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(7, new CyclicBarrierTest());
        for(int i = 0; i < 7; i++){
            Horse horse = new Horse(barrier);
            horses.add(horse);
            executorService.execute(horse);
        }
    }

}


class Horse implements Runnable {

    private static int counter = 0;
    private final int id = counter++;
    private int strides = 0;
    private static Random rand = new Random(47);
    private static CyclicBarrier barrier;

    public Horse(CyclicBarrier b) {
        barrier = b;
    }

    @Override
    public void run() {

        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    // 赛马每次随机跑几步
                    strides += rand.nextInt(3);
                }
                barrier.await();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public String tracks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getStrides(); i++) {
            sb.append("*");
        }
        sb.append(id);
        return sb.toString();
    }

    public synchronized int getStrides() { return strides; }
    @Override
    public String toString() { return "Horse " + id + " "; }

}
