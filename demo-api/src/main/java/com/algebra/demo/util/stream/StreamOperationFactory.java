package com.algebra.demo.util.stream;

import com.algebra.demo.util.stream.opr.StreamDistinct;
import com.algebra.demo.util.stream.opr.StreamFilter;

/**
 * @author al
 * @date 2020/11/23 10:38
 * @description
 */
public class StreamOperationFactory {

    public static StreamOperation<Integer> getStreamOperation(String type) {
        StreamOperation<Integer> operation = null;
        switch (type) {
            case "filter":
                operation = new StreamFilter<>();
                break;
            case "distinct":
                operation = new StreamDistinct<>();
                break;
            default:
                break;
        }
        return operation;
    }

}
