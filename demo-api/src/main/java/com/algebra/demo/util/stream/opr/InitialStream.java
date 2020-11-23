package com.algebra.demo.util.stream.opr;

import com.algebra.demo.util.stream.StreamOperation;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * @author al
 * @date 2020/11/23 10:47
 * @description
 */
public class InitialStream<T> {

    private String initialType;

    private Stream<T> stream;

    private Stream<String> lines;

    private T[] array;

    private List<T> list;

    public InitialStream(String initialType) {
        this.initialType = initialType;
        stream = Stream.empty();
    }

    public InitialStream(String initialType, List<T> list) {
        this.initialType = initialType;
        this.list = list;
        stream = list.stream();
    }

    public InitialStream(String initialType, T[] array) {
        this.initialType = initialType;
        this.array = array;
        stream = Arrays.stream(array);
    }

    public InitialStream(String initialType, String path) throws IOException {
        this.initialType = initialType;
        this.lines = Files.lines(Paths.get(path), Charset.defaultCharset());
    }

    public InitialStream(String initialType, UnaryOperator<T> f, T t) {
        this.initialType = initialType;
        stream = Stream.iterate(t, f).limit(5);
    }

    public InitialStream(String initialType, Supplier<T> f) {
        this.initialType = initialType;
        stream = Stream.generate(f).limit(5);
    }

    public Stream<T> getStream() {
        switch (initialType) {
            case "collection":
                System.out.println("集合stream");
                break;
            case "array":
                System.out.println("数组stream");
                break;
            case "value":
                stream = Stream.of(array);
                System.out.println("值stream");
                break;
            case "file":
                System.out.println("文件stream");
                break;
            case "iterator":
                System.out.println("函数iterator stream");
                break;
            case "generator":
                System.out.println("函数generator stream");
                break;
            default:
                System.out.println("默认空stream");
                break;
        }
        return stream;
    }

}
