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
public class StreamMap<T extends String> implements StreamOperation<T> {

    private Stream<String> integerStream;

    @Override
    public void getStream(String type) {
        List<String> integers = Arrays.asList("JAVA 8", "Lambdas", " In", "Action");
        integerStream = integers.stream();
    }

    @Override
    public void doMidOperator() {
        integerStream = this.integerStream.map(String::toLowerCase);
    }

    @Override
    public void doEndOperator() {
        List<String> collect = integerStream.collect(Collectors.toList());
        System.out.println(collect.toString());
    }
}
