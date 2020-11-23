package com.algebra.demo.util.stream;

import com.algebra.demo.util.stream.opr.InitialStream;

import java.util.stream.Stream;

/**
 * @author al
 * @date 2020/11/23 9:35
 * @description 流是从支持数据处理操作的源生成的元素序列，源可以是数组、文件、集合、函数。
 * 流不是集合元素，它不是数据结构并不保存数据，它的主要目的在于计算
 */
public class StreamTest1123 {


    public static void main(String[] args) {
        InitialStream<Integer> streamIni = new InitialStream<>("collection", new Integer[]{1, 2, 3, 4, 5, 6});
        Stream<Integer> stream = streamIni.getStream();


    }



}
