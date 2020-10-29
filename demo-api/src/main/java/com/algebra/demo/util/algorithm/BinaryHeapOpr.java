package com.algebra.demo.util.algorithm;

import java.util.Arrays;

/**
 * @author al
 * @date 2020/10/29 11:35
 * @description 【小灰的算法之旅-java版实现】- 二叉堆
 * 参考：https://www.cnblogs.com/noKing/p/7966272.html
 * https://blog.csdn.net/HinstenyHisoka/article/details/91817687
 * https://www.jianshu.com/p/6d3a12fe2d04
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

        // 利用上浮操作，插入一个元素：0
        int[] array = new int[]{1, 3, 2, 6, 5, 7, 8, 9, 10, 0};
        upAdjust(array);
        System.out.println(Arrays.toString(array));

        // 利用下沉操作，构建一个二叉堆
        int[] array2 = new int[]{7, 1, 3, 10, 5, 2, 8, 9, 6};
        buildHeap(array2);
        System.out.println(Arrays.toString(array2));

        // 删除一个数据，下沉操作
        int[] array3 = {3, 8, 7, 4, 6, 6, 6, 2, 3, 5};
        downAdjust(array3, 0, array3.length);
        System.out.println(Arrays.toString(array3));

    }


    // <----------------------------番外篇：构建大顶堆----------------------/>

    /**
     * 上浮
     *
     * @param array 数据数组
     */
    public static void upAdjustMax(int[] array) {
        // 先求出父子节点的下标
        int childrenIndex = array.length - 1;
        int parentIndex = (childrenIndex - 1) / 2;
        // 记录子节点数据，用于最后赋值
        int temp = array[childrenIndex];
        // 开始上浮
        while (childrenIndex > 0 && temp > array[parentIndex]) {
            // 直接单向赋值，无需做交换操作
            array[childrenIndex] = array[parentIndex];
            // 更新父子节点下标的值，下面两句代码顺序不可相反
            childrenIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
        // 最后赋值
        array[childrenIndex] = temp;
    }

    /**
     * 下沉节点
     *
     * @param index 要下浮的节点的下标
     * @param array 数据数组
     */
    public static void downAdjustMax(int index, int[] array) {
        // 先记录父节点及左子节点的下标
        int parentIndex = index;
        int childrenIndex = 2 * parentIndex + 1;
        // 记录父节点的值，用于最后赋值
        int temp = array[parentIndex];
        // 若有左子节点则继续
        while (childrenIndex <= array.length - 1) {
            // 若有右子节点，且右子节点比左子节点大，则将 childrenIndex 记录为右子节点的下标
            if (childrenIndex + 1 <= array.length - 1 && array[childrenIndex + 1] > array[childrenIndex]) {
                childrenIndex++;
            }
            // 如果子节点大于父节点，则无需下沉，直接返回
            if (temp >= array[childrenIndex]) {
                break;
            }
            // 直接单向赋值，无需做交替操作
            array[parentIndex] = array[childrenIndex];
            // 更新父子节点下标的值，下面两句代码顺序不可相反
            parentIndex = childrenIndex;
            childrenIndex = 2 * childrenIndex + 1;
        }
        // 最后赋值
        array[parentIndex] = temp;
    }

    /**
     * 构建二叉堆
     *
     * @param array 数据数组
     */
    public static void buildBinaryHeapMax(int[] array) {
        for (int i = (array.length / 2) - 1; i >= 0; i--) {
            downAdjustMax(i, array);
        }
    }

}
