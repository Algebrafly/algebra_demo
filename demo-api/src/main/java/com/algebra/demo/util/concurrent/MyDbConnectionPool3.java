package com.algebra.demo.util.concurrent;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author al
 * @date 2020/4/14 15:57
 * @description 数据库连接池实现
 * @version 3.0
 */
public class MyDbConnectionPool3 {

    /**
     * 空闲连接池
     */
    private LinkedBlockingQueue<Connection> idleConnectPool;

    /**
     * 活跃连接池
     */
    private LinkedBlockingQueue<Connection> busyConnectPool;

    /**
     * 当前正在被使用的连接数
     */
    private AtomicInteger activeSize = new AtomicInteger(0);

    /**
     * 最大连接数
     */
    private final int maxSize;

    public MyDbConnectionPool3(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * 连接池初始化
     */
    public void init(){
        idleConnectPool = new LinkedBlockingQueue<>();
        busyConnectPool = new LinkedBlockingQueue<>();
    }

    /**
     * 获取一个连接
     * @return 数据库连接
     */
    public Connection getConnection(){
        // 从空闲连接池去出一个连接
        Connection connection = idleConnectPool.poll();

        if(connection != null) {
            // 如果有连接，则放入活跃连接池
            busyConnectPool.offer(connection);
            System.out.println("get one useful connection");
            return connection;
        }

        // 空闲池中没有连接
        // 如果idle池中连接未满maxSize，就新建一个连接
        if (activeSize.get() < maxSize) {
            // 通过 activeSize.incrementAndGet() <= maxSize 这个判断
            // 解决 if(activeSize.get() < maxSize) 存在的线程安全问题
            if (activeSize.incrementAndGet() <= maxSize) {
                // 创建新连接
                connection = MyDbConnection.createConnection();
                busyConnectPool.offer(connection);
                return connection;
            }
        }

        // 如果空闲池中连接数达到maxSize， 则阻塞等待归还连接
        try {
            System.out.println("排队等待连接");
            // 阻塞获取连接，如果10秒内有其他连接释放，
            connection = idleConnectPool.poll(10000, TimeUnit.MILLISECONDS);
            if (connection == null) {
                System.out.println("等待超时");
                throw new RuntimeException("等待连接超时");
            }
            System.out.println("等待到了一个连接");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;

    }

    /**
     * 释放一个连接
     *
     * @param connection
     */
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            busyConnectPool.remove(connection);
            idleConnectPool.offer(connection);
        }
    }

    /**
     * 销毁连接池
     */
    public void destroy() {

    }

    /**
     * 定时对连接进行健康检查
     * 注意：只能对idle连接池中的连接进行健康检查，
     * 不可以对busyConnectPool连接池中的连接进行健康检查，因为它正在被客户端使用;
     */
    //@Scheduled(fixedRate = 60 * 1000)
    public void check() {
        for (int i = 0; i < activeSize.get(); i++) {
            Connection connection = idleConnectPool.poll();
            try {
                boolean valid = connection.isValid(2000);
                if (!valid) {
                    // 如果连接不可用，则创建一个新的连接
                    connection = MyDbConnection.createConnection();
                }
                // 放进一个可用的连接
                idleConnectPool.offer(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




}
