package com.algebra.demo.util.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author al
 * @date 2020/3/31 13:54
 * @description
 */
public class NioNormalTest {





    public static void readNio(String path) {

        FileInputStream in = null;
        try {
            in = new FileInputStream(new File(path));
            FileChannel fileChannel = in.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            int len = -1;
            while ((len = fileChannel.read(buffer)) != -1) {
                buffer.clear();

                byte[] bytes = buffer.array();

                String str = new String(bytes, 0 ,len);
                System.out.println(str);
                System.out.println("limit:" + buffer.limit() +" capacity： "+ buffer.capacity() + "position: " + buffer.position());

            }
            fileChannel.close();
        } catch (Exception e) {
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

    public static void writeNio(String path){
        FileOutputStream out = null;

        try {
            out = new FileOutputStream(new File(path));
            FileChannel fileChannel = out.getChannel();

            ByteBuffer src = StandardCharsets.UTF_8.encode("你好你好你好你好你好");

            // 字节缓冲的容量和limit会随着数据长度变化，不是固定不变的
           System.out.println("初始化容量和limit：" + src.capacity() + "," + src.limit());

            int len = 0;
            while ((len = fileChannel.write(src)) != 0) {
                System.out.println("写入长度："+len);
            }


            fileChannel.close();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void testReaderAndWriter(String path) {
        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream(new File(path));
            FileChannel inChannel = in.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            out = new FileOutputStream(new File("./temp.txt"));
            FileChannel outChannel = out.getChannel();

            int inLen = -1;

            while ((inLen = inChannel.read(buffer)) != -1) {
                buffer.flip();

                int outLen = 0;
                while ((outLen = outChannel.write(buffer)) != 0) {

                }

                buffer.clear();

            }


        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    public static void main(String[] args) {
        testReaderAndWriter("E:\\MyProject\\algebra_demo\\demo-api\\src\\main\\resources\\normal_io_2.txt");
    }



}
