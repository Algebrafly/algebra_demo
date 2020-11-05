package com.algebra.demo.util.interview;

import io.swagger.models.auth.In;

import java.util.Stack;

/**
 * @author al
 * @date 2020/11/5 15:05
 * @description 【小灰的算法之旅-java版实现】- 最小栈实现
 * 实现一个栈，该栈带有出栈、入栈、取最小元素的功能。保证这三个方法的时间复杂度都是O(1)
 */
public class MinStack {

     private Stack<Integer> mainStack = new Stack<>();
     private Stack<Integer> minStack = new Stack<>();


    /**
     * 入栈
     * @param element 入栈元素
     */
     public void push(int element){
         mainStack.push(element);
         // 如果辅助栈为空，或者新元素小于等于辅助栈中栈顶元素，则将新元素压入辅助栈
         if(minStack.empty() || element <= minStack.peek()){
             minStack.push(element);
         }
     }

    /**
     * 出栈
     * @return 栈顶元素
     */
     public Integer pop(){
         // 如果出栈元素和辅助栈栈顶元素相同，则辅助栈出栈
         if(mainStack.peek().equals(minStack.peek())){
             minStack.pop();
         }
         return mainStack.pop();
     }

    /**
     * 获取栈中最小值
     * @return 最小值
     * @throws Exception 空栈
     */
    public Integer getMin() throws Exception {
         if(mainStack.isEmpty()){
             throw new Exception("stack is empty!");
         }
         return minStack.peek();
    }

    public static void main(String[] args) throws Exception {

        MinStack stack = new MinStack();
        stack.push(4);
        stack.push(9);
        stack.push(7);
        stack.push(3);
        stack.push(8);
        stack.push(5);

        System.out.println(stack.getMin());

        stack.pop();
        stack.pop();
        stack.pop();

        System.out.println(stack.getMin());
    }

}
