package com.algebra.demo.util.stream.opr;

import com.algebra.demo.util.stream.StreamOperation;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author al
 * @date 2020/11/23 14:06
 * @description
 */
public class StreamJoin<T> implements StreamOperation<T> {

    private Stream<TestObj> integerStream;

    @Override
    public void getStream(String type) {
        TestObj obj1 = new TestObj("A", false, 123, "1");
        TestObj obj2 = new TestObj("B", true, 256, "1");
        TestObj obj3 = new TestObj("C", true, 777, "2");
        TestObj obj4 = new TestObj("D", false, 111, "3");
        List<TestObj> objs = new ArrayList<>();
        Collections.addAll(objs, obj1, obj2, obj3, obj4);

        integerStream = objs.stream();
    }

    @Override
    public void doMidOperator() {
        integerStream = integerStream.distinct();
    }

    @Override
    public void doEndOperator() {
        String collect = integerStream.map(TestObj::getName).collect(Collectors.joining(", "));
        System.out.println(collect);
    }
}
