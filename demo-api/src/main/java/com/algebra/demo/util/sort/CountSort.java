package com.algebra.demo.util.sort;

import java.util.Arrays;

/**
 * @author al
 * @date 2020/11/3 11:06
 * @description 【小灰的算法之旅-java版实现】- 计数排序
 */
public class CountSort {

    /**
     * 计数排序-简单版
     *
     * @param array 原数组
     * @return 排序后数组
     */
    public static int[] countSortV1(int[] array) {
        // 1.得到数列最大值
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        // 2.根据数列最大值确定统计数组长度
        int[] countArray = new int[max + 1];

        // 3.遍历数列，填充统计数组
        for (int i = 0; i < array.length; i++) {
            countArray[array[i]]++;
        }

        // 4.遍历统计数组
        int index = 0;
        int[] sortedArray = new int[array.length];
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                sortedArray[index++] = i;
            }
        }

        return sortedArray;
    }


    /**
     * 计数排序-升级版
     *
     * @param array 原数组
     * @return 排序数组
     */
    public static int[] countSortV2(int[] array) {

        // 1.得到数列的最大值和最小值并计算出差值
        int max = array[0];
        int min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        int d = max - min;
        // 2.创建统计数组u，并统计对应元素的个数
        int[] countArray = new int[d + 1];
        for (int i = 0; i < array.length; i++) {
            countArray[array[i] - min]++;
        }
        System.out.println("原始统计数组为："+Arrays.toString(countArray));
        // 3.统计数组做变形：后面的元素等于前面的元素之和
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }
        System.out.println("变形统计数组为："+Arrays.toString(countArray));
        // 4.倒序遍历原始数列，从统计数组找到正确位置，输出到结果数组
        int[] sortedArray = new int[array.length];
        for (int i = array.length-1; i >= 0; i--) {
            // explain:
            sortedArray[countArray[array[i]-min]-1]=array[i];
            countArray[array[i]-min]--;
        }

        return sortedArray;
    }

    public static void main(String[] args) {
        int[] array = new int[]{4, 4, 6, 5, 3, 2, 8, 1, 7, 5, 6, 0, 10};
        int[] sortedArray = countSortV2(array);
        System.out.println(Arrays.toString(sortedArray));

    }


}
