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

        // 使用绝对位置的put操作，position不会变化， 等于0
//        buffer.put(0,'A');
//        buffer.put(1,'a');
//        buffer.put(2,'1');
        // 使用相对操作，position会根据目标数组大小改变
        buffer.put(chars);

        buffer.position(3);

        System.out.println("capacity: "+ buffer.capacity()+"，limit: "+ buffer.limit() + "，position："+buffer.position());
//        buffer.clear();
        buffer.flip();
//        System.out.println(buffer.get(0));
        System.out.println("capacity: "+ buffer.capacity()+"，limit: "+ buffer.limit() + "，position："+buffer.position());


    }


}
