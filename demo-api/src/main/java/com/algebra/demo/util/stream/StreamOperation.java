package com.algebra.demo.util.stream;

import java.util.stream.Stream;

/**
 * @author al
 * @date 2020/11/23 10:39
 * @description
 */
public interface StreamOperation<T> {

    void getStream(String type);

    void doMidOperator();

    void doEndOperator();

}
