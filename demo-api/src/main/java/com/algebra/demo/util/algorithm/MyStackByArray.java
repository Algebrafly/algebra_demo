package com.algebra.demo.util.algorithm;

import java.util.Arrays;

/**
 * @author al
 * @date 2020/10/26 14:57
 * @description 【小灰的算法之旅-java版实现】- 栈结构的数组实现
 */
public class MyStackByArray<T> {

    /**
     * 实现栈的数组
     */
    private Object[] stack;

    /**
     * 数组大小
     */
    private int size;

    public MyStackByArray(int size) {
        this.size = size;
        this.stack = new Object[size];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T peek() {
        T t = null;
        if (size > 0) {
            t = (T) stack[size - 1];
        }
        return t;
    }

    public void push(T t) {
        expandCapacity(size+1);
        stack[size] = t;
        size++;
    }

    public T pop() {
        T t = peek();
        if (size > 0) {
            stack[size - 1] = null;
            size--;
        }
        return t;
    }

    public void expandCapacity(int size) {
        int len = stack.length;
        if (size > len) {
            size = size * 3 / 2 + 1;
            stack = Arrays.copyOf(stack, size);
        }
    }

    public void output(){
        for (int i = 0; i < size; i++) {
            System.out.print(stack[i] + "->");
        }
    }

    public static void main(String[] args) {
        MyStackByArray<String> stack = new MyStackByArray<>(3);
        System.out.println(stack.peek());
        System.out.println(stack.isEmpty());
        stack.push("java");
        stack.push("is");
        stack.push("beautiful");
        stack.push("language");
        stack.output();
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
    }


}
