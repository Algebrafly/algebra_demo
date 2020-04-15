package com.algebra.demo.util.concurrent;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author al
 * @date 2020/4/14 14:31
 * @description database connections
 */
public class MyDbConnection {

    private static final String PROP_PATH = "db.properties";
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    public MyDbConnection () {
        createConnection();
    }

    static {
        try {
            InputStream in = MyDbConnection.class.getClassLoader().getResourceAsStream(PROP_PATH);
            Properties props = new Properties();
            props.load(in);
            driver = props.getProperty("driver");
            url = props.getProperty("url");
            username = props.getProperty("username");
            password = props.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 建立一个数据库连接
     *
     * @return 数据库连接
     */
    public static Connection createConnection() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭数据库连接
     * @param resultSet 结果集
     * @param statement executing a static SQL statement
     * @param connection 数据库连接
     */
    public static void closeJDBC(ResultSet resultSet, Statement statement, Connection connection){

        if(resultSet!=null) {
            try {
                resultSet.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        if(statement!=null) {
            try {
                statement.close();
            } catch (SQLException e) {
                statement=null;
            }
        }

        if(connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                connection=null;
            }
        }

    }

    /**
     * 执行插入操作
     *
     * @param connection 数据库连接
     * @param insertSql SQL语句
     */
    public static void executeInsert(Connection connection, String insertSql) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertSql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
    }

    public static void main(String[] args) {

        System.out.println(createConnection());

    }


}
