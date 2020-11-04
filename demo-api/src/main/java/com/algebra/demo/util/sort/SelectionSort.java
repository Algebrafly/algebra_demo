package com.algebra.demo.util.sort;

import java.util.Arrays;

/**
 * @author al
 * @date 2020/11/4 9:05
 * @description 【myself】-选择排序
 */
public class SelectionSort {

    /**
     * 每一轮排序后，都会挑选出最小的元素放到数组头部
     * @param array 原数组
     */
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {4, 5, 1, 3, 9, 8, 6, 7, 0, 2};
        selectionSort(array);
        System.out.println(Arrays.toString(array));
    }


}
