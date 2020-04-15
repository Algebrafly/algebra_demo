package com.algebra.demo.util.concurrent;

import java.sql.Connection;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author al
 * @date 2020/4/14 15:46
 * @description
 */
public class MyConnectionPoolTest3 extends Thread {

    private String insertSql;
    private CountDownLatch countDownLatch;
    private static MyDbConnectionPool3 pool3 = new MyDbConnectionPool3(10);

    public MyConnectionPoolTest3(CountDownLatch latch,String insertSql){
        this.countDownLatch = latch;
        this.insertSql = insertSql;
    }


    @Override
    public void run() {
        System.out.println("请求线程:" + Thread.currentThread().getName());
        // 从连接池获取连接
        Connection connection = pool3.getConnection();
        // 模拟插入数据
        MyDbConnection.executeInsert(connection, String.format(insertSql, UUID.randomUUID().toString()));
        pool3.releaseConnection(connection);
        countDownLatch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {

        String insertSql = "INSERT INTO `user_tbl` (`name`) VALUES ('%s');";
        CountDownLatch latch = new CountDownLatch(10);

        System.out.println("开始执行SQL...");

        for (int i = 0; i < 10; i++) {
            new MyConnectionPoolTest3(latch,insertSql).start();
        }
        latch.await();

        System.out.println("执行完毕！");

    }
}
