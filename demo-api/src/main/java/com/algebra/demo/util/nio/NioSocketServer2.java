package com.algebra.demo.util.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author al
 * @date 2020/4/9 16:09
 * @description
 */
public class NioSocketServer2 {

    private volatile byte flag = -1;

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public void start(){

        //创建serverSocketChannel， 监听8888端口
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()){
            serverSocketChannel.socket().bind(new InetSocketAddress(8888));
            serverSocketChannel.configureBlocking(false);
            // 为serverChannel注册selector
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("server start working ...");

            // 创建消息处理器



        } catch (Exception e) {
            e.printStackTrace();

        }




    }

}
