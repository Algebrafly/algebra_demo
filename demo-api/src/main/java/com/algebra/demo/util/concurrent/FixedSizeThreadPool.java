package com.algebra.demo.util.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author al
 * @date 2020/4/16 10:55
 * @description 简易线程池
 */
public class FixedSizeThreadPool {

    /**
     * 阻塞队列内存放要执行的任务
     */
    private BlockingDeque<Runnable> blockingDeque;

    /**
     * 线程集合去执行任务
     */
    private List<Thread> threads;


    public static class Worker extends Thread {

        private FixedSizeThreadPool fixedSizeThreadPool;

        public Worker(FixedSizeThreadPool fixedSizeThreadPool){
            this.fixedSizeThreadPool = fixedSizeThreadPool;
        }

        @Override
        public void run() {
            while (true) {
                Runnable runnable = null;
                try {
                    runnable = this.fixedSizeThreadPool.blockingDeque.take();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(runnable != null){
                    runnable.run();
                    System.out.println("线程"+Thread.currentThread().getName()+"执行完毕");
                }
            }
        }
    }

    public FixedSizeThreadPool(int poolSize, int taskSize){
        this.blockingDeque = new LinkedBlockingDeque<>(taskSize);
        this.threads = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < poolSize; i++) {
            Worker worker = new Worker(this);
            threads.add(worker);
            worker.start();
        }
    }

    public boolean submit(Runnable task) {
        return this.blockingDeque.offer(task);
    }

}
