package com.algebra.demo.util.concurrent;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author al
 * @date 2020/4/14 15:57
 * @description 数据库连接池实现
 * @version 2.0
 */
public class MyDbConnectionPool2 {

    private static String PROP_PATH = "db.properties";
    private static String max = null;
    private static String min = null;
    //记录连接池中的数量
    private static int poolSize = 0;

    public MyDbConnectionPool2() {}

    // 更加高效的线程安全容器
    private static ConcurrentLinkedQueue<Connection> pool = new ConcurrentLinkedQueue<>();

    static {
        if (poolSize == 0 && pool.isEmpty()) {
            synchronized (MyDbConnectionPool2.class) {
                if(poolSize == 0 && pool.isEmpty()){
                    try {
                        System.out.println("connection pool is empty, initial pool");
                        InputStream in = MyDbConnectionPool2.class.getClassLoader().getResourceAsStream(PROP_PATH);
                        Properties prop = new Properties();
                        prop.load(in);

                        max = prop.getProperty("max");
                        min = prop.getProperty("min");
                        for (int i = 0; i < Integer.parseInt(min); i++) {
                            pool.add(MyDbConnection.createConnection());
                            poolSize++;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public static Connection getConnection() {
        try {
            if (!pool.isEmpty()) {
                System.out.println("连接池非空，直接返回");
                //return (pool).removeFirst();
                return pool.remove();
            } else {
                System.out.println("连接池为空,进行扩容");
                expland(5);
                System.out.println("等待获取");
                while (true) {
                    if (!pool.isEmpty()) {
                        System.out.println("获取成功,返回");
                        //return pool.removeFirst();
                        return pool.remove();
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 销毁连接池
     */
    public static void distory(){
        for (Connection conn : pool) {
            try {
                conn.close();
                pool.remove(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        poolSize = 0;
        System.out.println("销毁成功，未使用：" + getAvaliableSize() + "总数：" + getPoolSize());
    }

    /**
     * 回收连接
     * @param conn
     */
    public static void recycle(Connection conn){
        pool.add(conn);
        System.out.println("回收成功，未使用：" + getAvaliableSize() + "总数：" + getPoolSize());
    }

    /**
     * 获取当前连接池有多少个连接，包括已使用和未使用
     * @return
     */
    public static int getPoolSize(){
        return poolSize;
    }

    /**
     * 获取未使用的数量
     * @return
     */
    public static int getAvaliableSize(){
        return pool.size();
    }

    /**
     * 线程池扩容
     * @num 增加的数量
     */
    private static void expland(int num) {
        if (poolSize > Integer.parseInt(max)) {
            System.out.println("总连接数量大于最大值，直接返回");
            return;
        }
        for (int i=0; i<num; i++) {
            pool.add(MyDbConnection.createConnection());
            poolSize++;
        }
        System.out.println("扩容成功，未使用：" + getAvaliableSize() + "总数：" + getPoolSize());
    }





}
