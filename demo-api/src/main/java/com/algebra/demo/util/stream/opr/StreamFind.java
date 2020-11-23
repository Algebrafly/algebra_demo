package com.algebra.demo.util.stream.opr;

import com.algebra.demo.util.stream.StreamOperation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author al
 * @date 2020/11/23 14:06
 * @description
 */
public class StreamFind<T extends Integer> implements StreamOperation<T> {

    private Stream<Integer> integerStream;

    @Override
    public void getStream(String type) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        integerStream = integers.stream();
    }

    @Override
    public void doMidOperator() {
        integerStream = integerStream.filter(i -> i > 3);
    }

    @Override
    public void doEndOperator() {
        List<Integer> collect = integerStream.collect(Collectors.toList());
        System.out.println(collect.toString());
        Optional<Integer> first = integerStream.findFirst();
        Optional<Integer> any = integerStream.findAny();
        System.out.println(first.isPresent() ? first.get() : "null");
        System.out.println(first.isPresent() ? any.get() : "null");

    }
}
