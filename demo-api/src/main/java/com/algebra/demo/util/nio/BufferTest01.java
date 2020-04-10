package com.algebra.demo.util.nio;

import java.nio.CharBuffer;

/**
 * @author al
 * @date 2020/4/10 11:06
 * @description
 */
public class BufferTest01 {

    public static void main(String[] args) {

        // capacity 的值为4, limit 为2, 只能读取更新下标为0,1的数据.其他的无法操作

        char[] chars = new char[]{'1','a','b','c'};
        CharBuffer buffer = CharBuffer.wrap(chars);
        System.out.println("capacity: "+ buffer.capacity()+"，limit: "+ buffer.limit());

        buffer.limit(2);

        buffer.get(0);
        buffer.get(1);
        buffer.get(2);
        buffer.get(3);

        System.out.println("capacity: "+ buffer.capacity()+"，limit: "+ buffer.limit());


    }


}
