package com.algebra.demo.util.algorithm;

/**
 * @author al
 * @date 2020/10/26 15:17
 * @description 【小灰的算法之旅-java版实现】- 栈结构的链表实现
 */
public class MyStackByLinked<T> {

    static class Node<T> {
        T t;
        Node<T> next;

        Node(T t) {
            this.t = t;
        }
    }

    private Node<T> head;

    private int size;

    public MyStackByLinked() {
        head = null;
    }

    public void push(T t) {
        if (t == null) {
            throw new NullPointerException("参数不能为空");
        }
        if (head == null) {
            head = new Node<>(t);
            head.next = null;
        } else {
            Node<T> temp = head;
            head = new Node<>(t);
            head.next = temp;
        }
        size++;
    }

    public T pop() {
        T t = head.t;
        head = head.next;
        size--;
        return t;
    }

    public T peek() {
        return head.t;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void output() {
        Node<T> temp = head;
        while (temp != null) {
            System.out.print(temp.t+" <| ");
            temp = temp.next;
        }
    }


    public static void main(String[] args) {
        MyStackByLinked<String> stack = new MyStackByLinked<>();
        System.out.println(stack.isEmpty());
        stack.push("Java");
        stack.push("is");
        stack.push("beautiful");
        stack.output();
        System.out.println();
        System.out.println(stack.peek());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
    }


}
