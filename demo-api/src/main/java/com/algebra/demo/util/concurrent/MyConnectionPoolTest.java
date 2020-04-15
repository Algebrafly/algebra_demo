package com.algebra.demo.util.concurrent;

/**
 * @author al
 * @date 2020/4/14 15:46
 * @description
 */
public class MyConnectionPoolTest extends Thread {

    private static MyDbConnectionPool pool = new MyDbConnectionPool(3);

    @Override
    public void run() {
        try {
            MyDbConnection connect = pool.openConnect();
            Thread.sleep(1000);
            pool.release(connect);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) {
            new MyConnectionPoolTest().start();
        }
    }
}
