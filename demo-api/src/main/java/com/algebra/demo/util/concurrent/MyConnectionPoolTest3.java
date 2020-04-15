package com.algebra.demo.util.concurrent;

import java.sql.Connection;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * @author al
 * @date 2020/4/14 15:46
 * @description
 */
public class MyConnectionPoolTest3 extends Thread {

    private static int executorThreadSize = 20;
    private static String insertSql = "INSERT INTO `user_tbl` (`name`) VALUES ('%s');";
    private static CountDownLatch countDownLatch = new CountDownLatch(10);

    private static MyDbConnectionPool3 pool3 = new MyDbConnectionPool3(executorThreadSize);

    public static void main(String[] args) {

        System.out.println("请求线程:" + Thread.currentThread().getName());
        new Thread(() -> {
            countDownLatch.countDown();
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                // 从连接池获取连接
                Connection connection = pool3.getConnection();
                // 模拟插入数据
                MyDbConnection.executeInsert(connection, String.format(insertSql, UUID.randomUUID().toString()));
                pool3.releaseConnection(connection);// 释放连接
            }
        }).start();



    }
}
