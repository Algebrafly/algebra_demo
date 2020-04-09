package com.algebra.demo.util.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author al
 * @date 2020/4/3 10:30
 * @description
 */
public class NioSocketServerConnect {

    private static final int BUF_SIZE=1024;
    private static final int PORT = 8888;
    private static final int TIMEOUT = 3000;

    public static void main(String[] args) {

        selector();

    }


    public static void handleAccept (SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(key.selector(),SelectionKey.OP_READ, ByteBuffer.allocateDirect(BUF_SIZE));
    }

    public static void handleRead(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel)key.channel();
        ByteBuffer buf = (ByteBuffer)key.attachment();
        long bytesRead = sc.read(buf);
        while(bytesRead>0){
            buf.flip();
            while(buf.hasRemaining()){
                System.out.print((char)buf.get());
            }
            System.out.println();
            buf.clear();
            bytesRead = sc.read(buf);
        }
        if(bytesRead == -1){
            sc.close();
        }
    }

    public static void handleWrite(SelectionKey key) throws IOException{
        ByteBuffer buf = (ByteBuffer)key.attachment();
        buf.flip();
        SocketChannel sc = (SocketChannel) key.channel();
        while(buf.hasRemaining()){
            sc.write(buf);
        }
        buf.compact();
    }

    public static void selector() {
        Selector selector = null;
        ServerSocketChannel channel = null;

        try {
            selector = Selector.open();
            channel = ServerSocketChannel.open();
            channel.socket().bind(new InetSocketAddress(PORT));
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                if(selector.select(TIMEOUT) == 0){
                    System.out.println("==");
                    continue;
                }
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while(iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if(key.isAcceptable()){
                        System.out.println("accept");
                        handleAccept(key);
                    }
                    if(key.isReadable()){
                        System.out.println("read");
                        handleRead(key);
                    }
                    if(key.isWritable() && key.isValid()){
                        System.out.println("write");
                        handleWrite(key);
                    }
                    if(key.isConnectable()){
                        System.out.println("isConnectable = true");
                    }
                    iterator.remove();
                }


            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try{
                if(selector != null){
                    selector.close();
                }
                if(channel != null){
                    channel.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }

    }








}
