package com.algebra.demo.util.nio;

import java.nio.CharBuffer;

/**
 * @author al
 * @date 2020/4/10 11:06
 * @description
 */
public class BufferTest02 {

    public static void main(String[] args) {

        char[] chars = new char[]{'1','a','b','c'};
        CharBuffer buffer = CharBuffer.allocate(1024);
        System.out.println("[initial] capacity: "+ buffer.capacity()+"，limit: "+ buffer.limit() + "，position："+buffer.position());

//        buffer.put(0,'A');
//        buffer.put(1,'a');
//        buffer.put(2,'1');
        buffer.put(chars);

        buffer.position(3);

        System.out.println("capacity: "+ buffer.capacity()+"，limit: "+ buffer.limit() + "，position："+buffer.position());
//        buffer.clear();
        buffer.flip();
//        System.out.println(buffer.get(0));
        System.out.println("capacity: "+ buffer.capacity()+"，limit: "+ buffer.limit() + "，position："+buffer.position());


    }


}
