package com.algebra.demo.util.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @author al
 * @date 2020/4/3 10:30
 * @description
 */
public class NioSocketServerConnect2 {

    private int bufferSize = 1024;
    private String localCharset = "UTF-8";

    public NioSocketServerConnect2(){}

    public NioSocketServerConnect2(int bufferSize){
        this(bufferSize,null);
    }

    public NioSocketServerConnect2(String localCharset){
        this(-1, localCharset);
    }

    public NioSocketServerConnect2(int bufferSize, String localCharset){
        this.bufferSize = bufferSize > 0 ? bufferSize: this.bufferSize;
        this.localCharset = localCharset == null? this.localCharset: localCharset;
    }


    public void handleAccept(SelectionKey key) throws IOException {
        // 获取channel
        SocketChannel socketChannel = ((ServerSocketChannel) key.channel()).accept();
        // 非阻塞
        socketChannel.configureBlocking(false);
        // 注册selector
        socketChannel.register(key.selector(), SelectionKey.OP_ACCEPT, ByteBuffer.allocate(bufferSize));
    }


    public String handleRead(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();

        ByteBuffer buffer = (ByteBuffer) key.attachment();

        String receivedStr = "";

        if (socketChannel.read(buffer) == -1) {
            // 没读到内容关闭
            socketChannel.shutdownOutput();
            socketChannel.shutdownInput();
            socketChannel.close();
            System.out.println("connection already offline ...");
        } else {
            //将channel改为读取状态
            buffer.flip();
            //按照编码读取数据
            receivedStr = Charset.forName(localCharset).newDecoder().decode(buffer).toString();
            buffer.clear();

            //返回数据给客户端
            buffer = buffer.put(("received string : " + receivedStr).getBytes(localCharset));
            //读取模式
            buffer.flip();
            socketChannel.write(buffer);
            //注册selector 继续读取数据
            socketChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));

        }

        return receivedStr;
    }



}
