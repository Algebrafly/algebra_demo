package com.algebra.demo.util.interview;

import java.util.Stack;

/**
 * @author al
 * @date 2020/11/6 14:32
 * @description 【小灰的算法之旅-java版实现】- 用两个栈实现队列
 */
public class TwoStackQueue {

    private Stack<Integer> stackIn = new Stack<>();
    private Stack<Integer> stackOut = new Stack<>();

    /**
     * 入队
     * @param element 入队元素
     */
    public void enQueue(int element){
        stackIn.push(element);
    }

    /**
     * 出队
     * @return element
     */
    public Integer deQueue(){
        if(stackOut.isEmpty()){
            if(stackIn.isEmpty()){
                return null;
            }
            // 将In栈中元素转移到Out栈中元素
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.pop();
    }


    public static void main(String[] args) {

        TwoStackQueue stackQueue = new TwoStackQueue();
        stackQueue.enQueue(1);
        stackQueue.enQueue(2);
        stackQueue.enQueue(3);

        System.out.println(stackQueue.deQueue());

        stackQueue.enQueue(4);

        System.out.println(stackQueue.deQueue());


    }

}
