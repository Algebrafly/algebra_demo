package com.algebra.demo.util.stream.opr;

import com.algebra.demo.util.stream.StreamOperation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author al
 * @date 2020/11/23 14:06
 * @description
 */
public class StreamMaxOrMin<T extends Integer> implements StreamOperation<T> {

    private Stream<Integer> integerStream;

    @Override
    public void getStream(String type) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        integerStream = integers.stream();
    }

    @Override
    public void doMidOperator() {
        integerStream = integerStream.distinct();
    }

    @Override
    public void doEndOperator() {
        List<Integer> collect = integerStream.collect(Collectors.toList());
        System.out.println(collect.toString());
        Optional<Integer> max = integerStream.max(Integer::compareTo);
        System.out.println("Max = "+ (max.isPresent() ? max.get() : "null"));

        Optional<Integer> min = integerStream.min(Integer::compareTo);
        System.out.println("Min = "+ (min.isPresent() ? min.get() : "null"));

        OptionalInt min1 = integerStream.mapToInt(Integer::intValue).min();
        System.out.println("min1 = "+ min1.getAsInt());

    }
}
