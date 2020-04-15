package com.algebra.demo.util.concurrent;

import java.sql.Connection;

/**
 * @author al
 * @date 2020/4/14 15:46
 * @description
 */
public class MyConnectionPoolTest2 extends Thread {

    public static void main(String[] args) {

        for (int i=0;i<18;i++) {
            //DbUtil.getConnection();
            System.out.println("从连接池获取: " + MyDbConnectionPool2.getConnection().hashCode());

        }
        MyDbConnectionPool2.distory();

        Connection connection1 = MyDbConnectionPool2.getConnection();
        Connection connection2 = MyDbConnectionPool2.getConnection();
        Connection connection3 = MyDbConnectionPool2.getConnection();

        MyDbConnectionPool2.recycle(connection1);


    }
}
