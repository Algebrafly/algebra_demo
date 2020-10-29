package com.algebra.demo.util.algorithm;

import java.util.Arrays;

/**
 * @author al
 * @date 2020/10/29 11:35
 * @description 【小灰的算法之旅-java版实现】- 二叉堆
 */
public class BinaryHeapOpr {


    /**
     * 二叉堆的上浮调整-小顶堆（插入）
     * 数组实现
     * 数组最后一个元素的上浮操作
     *
     * @param array 待调整的堆
     */
    public static void upAdjust(int[] array) {
        int childIndex = array.length - 1;
        int parentIndex = (childIndex - 1) / 2;

        // temp 保存插入的叶子节点，用于最后的赋值
        int temp = array[childIndex];

        while (childIndex > 0 && temp < array[parentIndex]) {
            // 单项赋值
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
        array[childIndex] = temp;
    }


    /**
     * 二叉堆的下沉调整-小顶堆（删除，构建）
     * 数组实现
     *
     * @param array       待调整的堆
     * @param parentIndex 要下沉的父节点
     * @param length      堆的有效大小
     */
    public static void downAdjust(int[] array, int parentIndex, int length) {
        // temp保存父节点值，用户最后的赋值
        int temp = array[parentIndex];
        int childIndex = 2 * parentIndex + 1;
        while (childIndex < length) {
            // 如果有右孩子，且右孩子小于左孩子的值，则定位到右孩子
            if (childIndex + 1 < length && array[childIndex + 1] < array[childIndex]) {
                childIndex++;
            }
            // 如果父节点小于任何一个孩子的值，则直接跳出
            if (temp <= array[childIndex]) {
                break;
            }
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        array[parentIndex] = temp;
    }

    public static void buildHeap(int[] array) {
        // 从最后一个非叶子节点开始，以此做下沉调整
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            downAdjust(array, i, array.length);
        }
    }

    public static void main(String[] args) {

        int[] array = new int[]{1, 3, 2, 6, 5, 7, 8, 9, 10, 0};
        upAdjust(array);
        System.out.println(Arrays.toString(array));


        int[] array2 = new int[]{7, 1, 3, 10, 5, 2, 8, 9, 6};
        buildHeap(array2);
        System.out.println(Arrays.toString(array2));

    }


}
