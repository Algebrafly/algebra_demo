package com.algebra.demo.util.nio;

import org.checkerframework.checker.units.qual.C;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
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
            // 通过设置字符集的编码，并将ByteBuffer转换为CharBuffer来避免中文乱码
            Charset charset = StandardCharsets.UTF_8;
            CharsetDecoder decoder = charset.newDecoder();

            aFile = new RandomAccessFile("E:\\MyProject\\algebra_demo\\demo-api\\src\\main\\resources\\normal_io.txt", "rw");
            FileChannel fileChannel = aFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            CharBuffer charBuffer = CharBuffer.allocate(1024);
            int bytesRead = fileChannel.read(byteBuffer);

            // 临时存放转码后的字符
            char[] charCache = null;
            // 存放decode操作后未处理完的字节。decode仅仅转码尽可能多的字节，此次转码不了的字节需要缓存，下次再转
            byte[] remainByte = null;
            // 未转码的字节数
            int leftNum = 0;
            while (bytesRead != -1) {
                // 切换buffer从写模式到读模式
                byteBuffer.flip();
                // 以utf8编码转换ByteBuffer到CharBuffer
                decoder.decode(byteBuffer, charBuffer, true);
                // 切换buffer从写模式到读模式
                charBuffer.flip();
                remainByte = null;

                leftNum = byteBuffer.limit() - byteBuffer.position();
                if(leftNum > 0){
                    // 记录未转换完的字节
                    remainByte = new byte[leftNum];
                    byteBuffer.get(remainByte, 0 ,leftNum);
                }

                // 输出已转换的字符
                charCache = new char[charBuffer.length()];
                while (charBuffer.hasRemaining()) {
                    charBuffer.get(charCache);
                    System.out.println(new String(charCache));
                }
                // 切换buffer从读模式到写模式
                byteBuffer.clear();
                charBuffer.clear();
                if(remainByte != null){
                    // 将未转换完的字节写入bBuf，与下次读取的byte一起转换
                    byteBuffer.put(remainByte);
                }
                bytesRead = fileChannel.read(byteBuffer);
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
