package com.algebra.demo.util.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author al
 * @date 2020/3/9 16:18
 * @description
 */
public class NioTest01 {

    public static void method1() {

        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(new File("E:\\MyProject\\algebra_demo\\demo-api\\src\\main\\resources\\normal_io.txt")));

            String str;
            while ((str = in.readLine()) != null) {
                System.out.println(str);
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void method2() {
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream("E:\\MyProject\\algebra_demo\\demo-api\\src\\main\\resources\\normal_io.txt"));
            byte[] buf = new byte[1024];
            int bytesRead = in.read(buf);
            while (bytesRead != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    System.out.print((char) buf[i]);
                }
                bytesRead = in.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void methodNio() {

        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile("E:\\MyProject\\algebra_demo\\demo-api\\src\\main\\resources\\normal_io.txt", "rw");
            FileChannel fileChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            int bytesRead = fileChannel.read(buf);
            System.out.println(bytesRead);
            while (bytesRead != -1) {
                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }
                buf.compact();
                bytesRead = fileChannel.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (aFile != null) {
                    aFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    public static void main(String[] args) {

        methodNio();

    }


}
