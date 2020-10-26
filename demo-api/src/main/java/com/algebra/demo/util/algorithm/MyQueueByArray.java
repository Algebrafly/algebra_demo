package com.algebra.demo.util.algorithm;

/**
 * @author al
 * @date 2020/10/26 16:12
 * @description 【小灰的算法之旅-java版实现】- （循环）队列结构的数组实现
 */
public class MyQueueByArray<E> {

    private Object[] array;
    /**
     * 队头
     */
    private int front;
    /**
     * 队尾
     */
    private int rear;

    private int size;

    public MyQueueByArray(int capacity) {
        this.array = new Object[capacity];
    }

    public void enQueue(E data) throws Exception {
        if ((rear + 1) % array.length == front) {
            throw new Exception(" 队列已满！");
        }
        array[rear] = data;
        rear = (rear + 1) % array.length;
        size++;
    }

    public E deQueue() throws Exception {
        if(rear == front){
            throw new Exception(" 队列已空！");
        }
        E deQueueEle = (E) array[front];
        front = (front+1)%array.length;
        size--;
        return deQueueEle;
    }

    public void output(){
        for(int i = front; i != rear; i = (i+1)%array.length){
            System.out.print(array[i]+" << ");
        }
    }

    public static void main(String[] args) throws Exception {
        MyQueueByArray<String> myQueue = new MyQueueByArray<>(6);
        myQueue.enQueue("1");
        myQueue.enQueue("world");
        myQueue.enQueue("1");
        myQueue.enQueue("1");
        myQueue.deQueue();
        myQueue.enQueue("hello");

        myQueue.output();

    }


}
