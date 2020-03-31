package com.algebra.demo.util.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @author al
 * @date 2020/3/31 13:54
 * @description
 */
public class NioTest02 {

    public static void writeTest() {

        RandomAccessFile randomAccessFile = null;
        FileChannel channel = null;

        try {
            randomAccessFile = new RandomAccessFile("normal_io.txt","rw");
            channel = randomAccessFile.getChannel();

            FileLock lock = null;
            while (lock == null) {
                lock = channel.lock();
            }
            System.out.println("write process: get lock");



            lock.release();


        } catch (Exception e) {

        }

    }

    public static void readTest() {

        RandomAccessFile randomAccessFile = null;
        FileChannel channel = null;

        try {
            randomAccessFile = new RandomAccessFile("normal_io.txt","rw");
            channel = randomAccessFile.getChannel();
            FileLock lock = null;

            while (true) {
                lock = channel.tryLock();
                if(lock == null){
                    System.out.println("read process:get lock failed");
                    Thread.sleep(1000);
                } else {
                    break;
                }
            }

            System.out.println("read process:get lock");

            System.out.println(randomAccessFile.length());

            lock.release();
            System.out.println("read process: release lock");

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if(randomAccessFile != null){
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(channel != null){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        readTest();

    }



}
