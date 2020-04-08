package com.algebra.demo.util.socket;

import com.algebra.demo.util.thread.ThreadPoolManager3;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author al
 * @date 2020/4/8 10:58
 * @description
 */
public class ServerSocketThread implements Runnable {

    private Socket socket = null;
    private InetAddress inetAddress = null;

    public ServerSocketThread(Socket socket, InetAddress inetAddress) {
        this.socket = socket;
        this.inetAddress = inetAddress;
    }

    @Override
    public void run() {

        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            String req;
//            while ((req = bufferedReader.readLine()) != null) {
//                System.out.println("from client （ "+ socket.getInetAddress() +" ） receive message: "+ req);
//            }
            System.out.println("from client （ "+ socket.getInetAddress() +" ） receive message: "+ bufferedReader.readLine());

            // 向客户端返回消息的PrintWriter对象
            printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
            printWriter.println("server（ "+ inetAddress.getHostAddress() +" ）to client message：message received successful！");
            printWriter.flush();

            System.out.println("server end!");

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if(bufferedReader != null){
                    bufferedReader.close();
                }
                if(printWriter != null){
                    printWriter.close();
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
