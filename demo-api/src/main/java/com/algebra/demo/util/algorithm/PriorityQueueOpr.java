package com.algebra.demo.util.algorithm;

import java.util.Arrays;

/**
 * @author al
 * @date 2020/10/30 8:47
 * @description 【小灰的算法之旅-java版实现】- 优先队列
 * 参考：
 */
public class PriorityQueueOpr {

    private int[] array;

    private int size;

    public PriorityQueueOpr() {
        // 初始化长度为32
        array = new int[32];
    }

    /**
     * 最大优先队列-入队操作（相当于大顶堆的插入操作）
     *
     * @param key 入队元素
     */
    public void enQueue(int key) {
        if (size >= array.length) {
            resize();
        }
        array[size++] = key;
        upAdjust();
    }

    /**
     * 最大优先队列-出队操作（相当于大顶堆的删除堆顶操作）
     *
     * @return 删除元素
     * @throws Exception 空堆
     */
    public int deQueue() throws Exception {
        if (size <= 0) {
            throw new Exception("the queue is empty!");
        }
        // 堆顶元素
        int head = array[0];
        // 让最后一个元素移动到堆顶
        array[0] = array[--size];
        downAdjust();
        return head;
    }

    private void upAdjust() {
        int childIndex = size - 1;
        int parentIndex = (childIndex - 1) / 2;
        // temp 保存插入的叶子节点，用于最后的赋值
        int temp = array[childIndex];
        while (childIndex > 0 && temp > array[parentIndex]) {
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = parentIndex / 2;
        }
        array[childIndex] = temp;
    }

    private void downAdjust() {
        // temp 保存父节点的值，用于最后的赋值
        int parentIndex = 0;
        int temp = array[parentIndex];
        int childIndex = 1;
        while (childIndex < size) {
            // 如果有右孩子，且右孩子大于左孩子的值，则定位到右孩子
            if (childIndex + 1 < size && array[childIndex + 1] > array[childIndex]) {
                childIndex++;
            }
            // 如果父节点大于任何一个孩子的值，则直接跳出
            if (temp >= array[childIndex]) {
                break;
            }
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        array[parentIndex] = temp;
    }


    /**
     * 数组扩容
     */
    public void resize() {
        int newSize = this.size * 2;
        this.array = Arrays.copyOf(this.array, newSize);
    }

    public static void main(String[] args) throws Exception {
        PriorityQueueOpr opr = new PriorityQueueOpr();
        opr.enQueue(3);
        opr.enQueue(5);
        opr.enQueue(10);
        opr.enQueue(2);
        opr.enQueue(7);
        opr.printAsTree();

//        System.out.println(opr.deQueue());

//        opr.printAsTree();

    }

    /**
     * 打印树状大顶堆
     */
    public void printAsTree() {
        //首先遍历第一行
        int lineNum = 1;
        //lines是堆的层数
        int lines = (int) (Math.log(size) / Math.log(2)) + 1;
        int spaceNum = (int) (Math.pow(2, lines) - 1);
        for (int i = 1; i <= size; ) {
            //因为在[1...size]左闭右闭区间存数据，data[0]不存数据

            //每层都是打印这个区间[2^(层数-1) ... (2^层数)-1]。如果堆里的数不够(2^层数)-1个，那就打印到size。所以取min((2^层数)-1,size).
            for (int j = (int) Math.pow(2, lineNum - 1); j <= Math.min(size, (int) Math.pow(2, lineNum) - 1); j++) {
                //打印spaceNum个空格
                printSpace(spaceNum);
                //打印数据
                System.out.printf("%3s", array[j]);
                //图片中绿色方框
                System.out.printf("%3s", "");
                //打印spaceNum个空格
                printSpace(spaceNum);
                i++;
            }
            lineNum++;
            spaceNum = spaceNum / 2;
            System.out.println();
        }
    }

    public void printSpace(int n) {//打印n个空格(在这里用‘\t’来代替)
        for (int i = 0; i < n; i++) {
            System.out.printf("%3s", "");
        }
    }

}
