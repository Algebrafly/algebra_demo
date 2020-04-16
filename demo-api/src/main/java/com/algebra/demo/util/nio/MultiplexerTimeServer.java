package com.algebra.demo.util.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author al
 * @date 2020/4/16 11:16
 * @description
 */
public class MultiplexerTimeServer implements Runnable {

    private Selector selector;

    private ServerSocketChannel socketChannel;

    private volatile boolean stop;

    /**
     * 初始化多路复用器、绑定监听端口
     *
     * @param port 端口号
     */
    public MultiplexerTimeServer(int port){
        try {
            selector = Selector.open();
            socketChannel = ServerSocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.socket().bind(new InetSocketAddress(port), 1024);
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time server is start in port : " + port);

        } catch (IOException e) {
          e.printStackTrace();
          System.exit(1);
        }
    }

    public void stop(){
        this.stop = stop;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                //设置休眠时间为1s。无论有读写事件发生，selector每隔1s被唤醒一次。
                selector.select(1000);
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectedKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        // 多路复用器关闭后，所有注册在上面的Channel和Pipe等资源都会被自动去注册并关闭，所以不需要重复释放资源
        if(selector != null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if(key.isValid()){
            System.out.println("deal new connection message...");
            if(key.isAcceptable()){
                // accept the new connection
                System.out.println("accept the new connection!");
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                // 创建TCP三次握手，虚链路
                SocketChannel accept = serverSocketChannel.accept();
                accept.configureBlocking(false);
                accept.socket().setReuseAddress(true);
                // Add new connection to the selector
                accept.register(selector, SelectionKey.OP_READ);
            }

            if(key.isReadable()){
                // read data
                System.out.println("read data");
                SocketChannel sc = (SocketChannel) key.channel();
                // 不知道客户端发送的码流大小，作为例子，开辟1024的缓冲区。
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                // 返回值>0，表示有字节，对字节进行解码
                if(readBytes > 0){
                    readBuffer.flip(); // 读模式
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, StandardCharsets.UTF_8);
                    System.out.println("The time server receive order : "
                            + body);
                    //如果请求内容是"Query time order"就回复，否则回复"BAD ORDER"
                    String currentTime = "QUERY TIME ORDER"
                            .equalsIgnoreCase(body) ? new java.util.Date(
                            System.currentTimeMillis()).toString()
                            : "BAD ORDER";
                    doWrite(sc, currentTime);
                }

            }

            if(key.isWritable()){
                System.out.println("write data");

            }

            if(key.isConnectable()){
                System.out.println("maybe connection");

            }

        }
    }

    private void doWrite(SocketChannel channel, String response)
            throws IOException {
        if (response != null && response.trim().length() > 0) {
            byte[] bytes = response.getBytes();
            //异步操作，无法保证发送完，会出现"写半包"问题，需要注册写操作，然后不断轮询Selector是否发送完毕，例子中不处理，太麻烦。
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }

}
