package com.algebra.demo.util.stream.opr;

import com.algebra.demo.util.stream.StreamOperation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author al
 * @date 2020/11/23 14:24
 * @description
 */
public class StreamAllMatch<T extends Integer> implements StreamOperation<T> {

    private Stream<Integer> integerStream;

    @Override
    public void getStream(String type) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        integerStream = integers.stream();
    }

    @Override
    public void doMidOperator() {
        boolean b = this.integerStream.allMatch(i -> i > 3);
        System.out.println(b ? "值都大于3" : "存在小于3的值");
    }

    @Override
    public void doEndOperator() {
        List<Integer> collect = integerStream.collect(Collectors.toList());
        System.out.println(collect.toString());
    }
}
