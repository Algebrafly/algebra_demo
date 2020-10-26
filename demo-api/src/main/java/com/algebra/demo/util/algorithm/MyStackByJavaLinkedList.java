package com.algebra.demo.util.algorithm;

import java.util.LinkedList;

/**
 * @author al
 * @date 2020/10/26 15:28
 * @description 【小灰的算法之旅-java版实现】- 栈结构的JAVA-LinkedList实现
 */
public class MyStackByJavaLinkedList<T> {

    private LinkedList<T> stack = new LinkedList<>();

    public void push(T t){
        stack.addFirst(t);
    }

    public T pop(){
        return stack.removeFirst();
    }

    public T peek(){
        T t = null;
        if(!isEmpty()){
            t = stack.getFirst();
        }
        return t;
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }

    public void output(){
        for (T t : stack) {
            System.out.print(t+" , ");
        }
    }

    public static void main(String[] args) {
        MyStackByJavaLinkedList<String> stack = new MyStackByJavaLinkedList<>();
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        stack.push("java");
        stack.push("is");
        stack.push("beautiful");
        stack.output();
        System.out.println();
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
    }



}
