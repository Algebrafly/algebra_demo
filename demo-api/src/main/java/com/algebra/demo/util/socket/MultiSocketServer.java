package com.algebra.demo.util.socket;

import com.algebra.demo.util.thread.ThreadPoolManager3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author al
 * @date 2020/4/8 10:51
 * @description
 */
public class MultiSocketServer {

    private static final int PORT = 4567;

    /**
     * 获取线程池
     */
    public static ThreadPoolManager3 threadPool = ThreadPoolManager3.getInstance();

    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("server socket start:" + serverSocket.toString());
            // 等待请求，请求到来之前会一直阻塞
            while (true) {
                socket = serverSocket.accept();
                System.out.println("接受到客户端请求，请求地址：" + socket.getInetAddress());
                // 调用线程类处理请求
                ServerSocketThread serverSocketThread = new ServerSocketThread(socket, socket.getInetAddress());
                threadPool.execute(serverSocketThread);

                System.out.println("线程池中线程数目：" + threadPool.getExecutor().getPoolSize() + "，队列中等待执行的任务数目：" +
                        threadPool.getExecutor().getQueue().size() + "，已执行结束别的任务数目：" + threadPool.getExecutor().getCompletedTaskCount());
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if(serverSocket != null){
                    serverSocket.close();
                }
                if(socket != null){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


}
