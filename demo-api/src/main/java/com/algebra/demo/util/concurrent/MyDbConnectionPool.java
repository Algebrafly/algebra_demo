package com.algebra.demo.util.concurrent;

import com.alibaba.druid.pool.DruidConnectionHolder;

import java.sql.Connection;
import java.util.concurrent.Semaphore;

/**
 * @author al
 * @date 2020/4/14 14:18
 * @description Using Semaphore to implement a simple database connection pool
 * @version 1.0
 */
public class MyDbConnectionPool {

    /**
     * connection pool size
     */
    private int size;

    /**
     * database connection collection
     */
    private MyDbConnection[] connections;

    /**
     * connection state mark
     */
    private boolean[] connectionFlag;

    /**
     * Number of remaining connections
     */
    private volatile int available;

    /**
     * AQS semaphore
     */
    private Semaphore semaphore;


    public MyDbConnectionPool (int size) {
        this.size = size;
        this.available = size;
        semaphore = new Semaphore(size, true);
        connections = new MyDbConnection[size];
        connectionFlag = new boolean[size];
        initConnects();
    }

    /**
     * 初始化连接
     */
    private void initConnects() {
        //生成指定数量的数据库连接
        for(int i = 0; i < this.size; i++) {
            connections[i] = new MyDbConnection();
        }
    }


    /**
     * 获取数据库连接
     * @return connection
     */
    private synchronized MyDbConnection getConnect(){
        for(int i = 0; i < connectionFlag.length; i++) {
            //遍历集合找到未使用的连接
            if(!connectionFlag[i]) {
                //将连接设置为使用中
                connectionFlag[i] = true;
                //可用连接数减1
                available--;
                System.out.println("【"+Thread.currentThread().getName()+"】以获取连接      剩余连接数：" + available);
                //返回连接引用
                return connections[i];
            }
        }
        return null;
    }

    /**
     * 获取一个连接
     * @return
     * @throws InterruptedException
     */
    public MyDbConnection openConnect() throws InterruptedException {
        //获取许可证
        semaphore.acquire();
        //获取数据库连接
        return getConnect();
    }

    /**
     * 释放一个连接
     * @param connect
     */
    public synchronized void release(MyDbConnection connect) {
        for(int i = 0; i < this.size; i++) {
            if(connect == connections[i]){
                //将连接设置为未使用
                connectionFlag[i] = false;
                //可用连接数加1
                available++;
                System.out.println("【"+Thread.currentThread().getName()+"】以释放连接      剩余连接数：" + available);
                //释放许可证
                semaphore.release();
            }
        }
    }

    /**
     * 剩余可用连接数
     * @return
     */
    public int available() {
        return available;
    }


}
