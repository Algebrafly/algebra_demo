package com.algebra.demo.util.sort;

import java.util.Arrays;

/**
 * @author al
 * @date 2020/11/4 9:16
 * @description 【myself】-简单插入排序
 */
public class InsertionSort {

    public static void insertSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                } else {
                    break;
                }
            }
        }
    }


    public static void main(String[] args) {
        int[] array = {4, 5, 1, 3, 9, 8, 6, 7, 0, 2};
        insertSort(array);
        System.out.println(Arrays.toString(array));
    }

}
