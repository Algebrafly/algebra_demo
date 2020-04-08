package com.algebra.demo.util.socket;

import com.google.common.base.Charsets;

import java.io.*;
import java.net.Socket;

/**
 * @author al
 * @date 2020/4/8 11:10
 * @description
 */
public class MultiClientSocket {

    private static final String IP_ADDRESS = "127.0.0.1";
    private static final int PORT = 4567;

    public static void main(String[] args) {

        Socket socket = null;
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;

        try {
            while (true) {
                // 控制台输入数据
                System.out.println("请输入消息：");
                System.out.print("> ");
                String input = new BufferedReader(new InputStreamReader(System.in, Charsets.UTF_8)).readLine();
                if (input == null || input.length() == 0) {
                    continue;
                }
                if ("quit".equalsIgnoreCase(input) || "exit".equalsIgnoreCase(input)) {
                    System.exit(0);
                }

                socket = new Socket(IP_ADDRESS, PORT);
                System.out.println("socket client: " + socket);
                // 发消息给server端
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);

                //接受server端消息
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                printWriter.println(input);
                printWriter.flush();

                String rsp = bufferedReader.readLine();
                System.out.println("receive socket message: " + rsp);
            }

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
