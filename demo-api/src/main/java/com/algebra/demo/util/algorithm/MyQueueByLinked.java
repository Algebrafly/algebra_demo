package com.algebra.demo.util.algorithm;

/**
 * @author al
 * @date 2020/10/26 15:42
 * @description 【小灰的算法之旅-java版实现】- 队列结构的链表实现
 * 参考： https://blog.csdn.net/chenleixing/article/details/42392283
 */
public class MyQueueByLinked<E> {

    static class Node<E>{
        E data;
        Node<E> next;

        Node(E data){
            this.data = data;
        }
    }

    private Node<E> front;
    private Node<E> rear;
    private int size;

    public boolean isEmpty(){
        return front == null;
    }

    public void enQueue(E data){
        Node<E> eNode = new Node<>(data);
        if(front == null){
            front = eNode;
        } else {
            rear.next = eNode;
        }
        rear = eNode;
        size++;
    }

    public E deQueue(){
        if(isEmpty()){
            return null;
        }
        Node<E> put = front;
        front = front.next;
        size--;
        return put.data;
    }

    public void output(){
        Node<E> temp = front;
        while (temp != null){
            System.out.print(temp.data+" << ");
            temp = temp.next;
        }

    }

    public static void main(String[] args) {

        MyQueueByLinked<String> test = new MyQueueByLinked<>();
        test.enQueue("111");
        test.enQueue("222");
        test.enQueue("333");
        test.enQueue("Java");

        test.output();

        System.out.println();
        String s = test.deQueue();
        System.out.println(s);

        test.output();

    }



}
