package com.algebra.demo.util.sort;

import java.util.Arrays;

/**
 * @author al
 * @date 2020/11/2 9:05
 * @description 【小灰的算法之旅-java版实现】- 冒泡排序&鸡尾酒排序
 */
public class BubbleSort {

    public static void sortV1(int[] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                count++;
                int temp;
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        System.out.println(count);
    }

    /**
     * 记录最后一次交换位置
     * 解决：前半段有序序列重复循环
     *
     * @param array 原始数组
     */
    public static void sortV2(int[] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            // 有序标记，每一轮初始值都是true
            boolean isSorted = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                count++;
                int temp;
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    // 有元素交换，所以标记变为false
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
        System.out.println(count);
    }

    /**
     * 记录最后一次交换位置
     * 解决：后半段有序序列重复循环
     *
     * @param array 原始数组
     */
    public static void sortV3(int[] array) {
        int count = 0;
        // 记录最后一次交换的位置
        int lastExchangeIndex = 0;
        // 无序数列的边界，每次比较只需到此为止
        int sortBorder = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            // 有序标记，每一轮初始值都是true
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                count++;
                int temp;
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    // 有元素交换，所以标记变为false
                    isSorted = false;
                    // 记录位置
                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            if (isSorted) {
                break;
            }
        }
        System.out.println(count);
    }

    /**
     * 鸡尾酒排序
     * 1. 正想遍历，排序一次
     * 2. 反向遍历，排序一次
     * 3. 正向遍历，如果数据已经有序，则停止排序
     * 解决问题：部门片段有序，消耗循环次数判断
     *
     * @param array 原始数组
     */
    public static void sortV4(int[] array) {
        int tmp = 0;
        int count = 0;
        for (int i = 0; i < array.length / 2; i++) {
            // 有序标记，每一轮初始值都是true
            boolean isSorted = true;
            // 正向，奇数轮循环
            for (int j = i; j < array.length - i - 1; j++) {
                count++;
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }

            // 在做偶数轮之前，将isSorted置为true
            isSorted = true;
            // 逆向，偶数轮循环
            for (int j = array.length - i - 1; j > i; j--) {
                count++;
                if (array[j] < array[j - 1]) {
                    tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
        System.out.println(count);

    }


    public static void main(String[] args) {
        int[] array = new int[]{9, 2, 5, 3, 1, 4, 6, 7, 0, 8};
        sortV1(array);
        System.out.println(Arrays.toString(array));

        int[] array2 = new int[]{0, 1, 2, 3, 4, 5, 9, 7, 6, 8};
        sortV2(array2);
        System.out.println(Arrays.toString(array2));


        int[] array3 = new int[]{0, 1, 3, 2, 4, 5, 6, 7, 8, 9};
        sortV3(array3);
        System.out.println(Arrays.toString(array3));

        int[] array4 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        sortV4(array4);
        System.out.println(Arrays.toString(array4));
    }

}
