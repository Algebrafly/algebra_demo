package com.algebra.demo.util.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

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
            NioSocketServerConnect2 handler = new NioSocketServerConnect2(1024);

            while (flag == 1) {
                selector.select();
                System.out.println("开始处理请求：");

                // 获取selectionKeys并处理
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    try {
                        if(key.isAcceptable()){
                            handler.handleAccept(key);
                        }

                        if(key.isReadable()){
                            System.out.println(handler.handleRead(key));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();


                    }
                    iterator.remove();
                }
                System.out.println("请求处理完成！");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    public static void main(String[] args) {

        NioSocketServer2 server = new NioSocketServer2();
        new Thread(()->{
            try {
                Thread.sleep(10*60*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                server.setFlag((byte) 0);
            }
        }).start();
        server.start();
    }

}
