package com.algebra.demo.util.nio;

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
 * refer: https://www.cnblogs.com/cxxjohnson/p/9082567.html
 * https://www.cnblogs.com/carl10086/p/6180451.html
 * https://www.cnblogs.com/CQqf/p/10795656.html
 *
 * @description
 */
public class NioReaderAndWriterTest {

    public static File createFile(String path) throws IOException {
        File file = new File(path);
        if(file.exists()){
            if(file.isFile()){
                System.out.println("file already exit!");
            } else {
                System.out.println("path is not file type");
            }
        } else {
            file.createNewFile();
        }
        return file;
    }


    /**
     * 字符流读取文件
     * @param path 文件路径
     */
    public static void readerMethod1(String path) {
        BufferedReader in = null;
        BufferedWriter out = null;
        try {
            in = new BufferedReader(new FileReader(new File(path)));
            out = new BufferedWriter(new FileWriter(createFile("./temp2.txt")));

            // in = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
            // out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(createFile("./temp2.txt")), StandardCharsets.UTF_8));

            String str = null;
            while ((str = in.readLine()) != null) {
                System.out.println(str);
                out.write(str);
                out.newLine();
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if(in != null){
                    in.close();
                }
                if(out != null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * （缓存）字节流读取文件
     * @param path 文件路径
     */
    public static void readerMethod2(String path) {
        InputStream in = null;
        OutputStream out = null;
        try {
            // 输入流
            in = new BufferedInputStream(new FileInputStream(path));
            // 输出流
            out = new BufferedOutputStream(new FileOutputStream(createFile("./temp.txt")));

            // 指定一次性读多少字节
            byte[] buf = new byte[1024];
            int bytesRead = -1;
            while ((bytesRead = in.read(buf,0,buf.length)) != -1) {
                // 转换字符串,并输出到控制台
                String s = new String(buf, 0, bytesRead, StandardCharsets.UTF_8);
                System.out.println(s);

                // 写入临时文件
                out.write(buf, 0, bytesRead);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if(out != null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void methodTest(String path){
        Reader in = null;
        PrintWriter out = null;
        try {
            in = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8);
            out = new PrintWriter(new FileWriter(createFile("./temp.txt")));

            byte[] bytes = new byte[1024];
            int len = -1;
            while((len = in.read()) != -1){
//                System.out.println(len);
                String s = new String(bytes, StandardCharsets.UTF_8);
                System.out.println(s);
                out.write(len);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if(out != null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * NIO 读文件
     * @param path 文件路径
     */
    public static void methodNioReader(String path) {

        RandomAccessFile aFile = null;
        try {
            // 通过设置字符集的编码，并将ByteBuffer转换为CharBuffer来避免中文乱码
            Charset charset = StandardCharsets.UTF_8;
            CharsetDecoder decoder = charset.newDecoder();

            aFile = new RandomAccessFile(path, "rw");
            FileChannel fileChannel = aFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(16);
            CharBuffer charBuffer = CharBuffer.allocate(16);
            int bytesRead = fileChannel.read(byteBuffer);

            // 临时存放转码后的字符
            char[] charCache = null;
            // 存放decode操作后未处理完的字节。decode仅仅转码尽可能多的字节，此次转码不了的字节需要缓存，下次再转
            byte[] remainByte = null;
            // 未转码的字节数
            int leftNum = 0;
            while (bytesRead != -1) {
                System.out.println("buffer "+bytesRead);
                // 切换buffer从写模式到读模式
                byteBuffer.flip();
                // 以utf8编码转换ByteBuffer到CharBuffer
                decoder.decode(byteBuffer, charBuffer, true);
                // 切换buffer从写模式到读模式
                charBuffer.flip();
                remainByte = null;

                leftNum = byteBuffer.limit() - byteBuffer.position();
                System.out.println("buffer distance "+leftNum);
                if(leftNum > 0){
                    // 记录未转换完的字节
                    remainByte = new byte[leftNum];
                    byteBuffer.get(remainByte, 0 ,leftNum);
                }

                // 输出已转换的字符
                charCache = new char[charBuffer.length()];
                while (charBuffer.hasRemaining()) {
                    charBuffer.get(charCache);
                    String s = new String(charCache);

                    System.out.println(s);
                    System.out.println("[缓冲区存储的汉字数量]"+s.length());
                }
                // 切换buffer从读模式到写模式
                byteBuffer.clear();
                charBuffer.clear();
                if(remainByte != null){
                    // 将未转换完的字节写入bBuf，与下次读取的byte一起转换
                    byteBuffer.put(remainByte);
                }
                bytesRead = fileChannel.read(byteBuffer);

                System.out.println("----------------[完成一次channel读写]---------------");
            }

            fileChannel.close();
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

        methodNioReader("E:\\MyProject\\algebra_demo\\demo-api\\src\\main\\resources\\normal_io_2.txt");

    }


}
