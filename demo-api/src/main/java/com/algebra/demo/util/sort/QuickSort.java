package com.algebra.demo.util.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author al
 * @date 2020/11/2 14:35
 * @description 【小灰的算法之旅-java版实现】- 快速排序
 */
public class QuickSort {

    /**
     * 递归方法-快速排序
     *
     * @param array      原数组
     * @param startIndex 开始下标
     * @param endIndex   结束下标
     */
    public static void quickSort(int[] array, int startIndex, int endIndex) {
        // 递归结束条件：startIndex大于或等于endIndex时
        if (startIndex >= endIndex) {
            return;
        }

        // 得到基准元素位置
        int pivotIndex = partition2(array, startIndex, endIndex);
        // 根据基准元素，分成两部分进行递归排序
        quickSort(array, startIndex, pivotIndex - 1);
        quickSort(array, pivotIndex + 1, endIndex);
    }

    /**
     * 栈方式-快速排序
     *
     * @param array      原数组
     * @param startIndex 开始下标
     * @param endIndex   结束下标
     */
    public static void quickSortNoRecursion(int[] array, int startIndex, int endIndex) {

        // 用一个集合栈来代替递归的函数栈
        Stack<Map<String, Integer>> quickSortStack = new Stack<>();

        // 整个数列的起止下标，以哈希的形式入栈
        Map<String, Integer> rootMap = new HashMap<>();
        rootMap.put("startIndex", startIndex);
        rootMap.put("endIndex", endIndex);
        quickSortStack.push(rootMap);

        // 循环条件结束：当栈为空时候
        while (!quickSortStack.isEmpty()) {
            // 栈顶元素出栈，得到起止下标
            Map<String, Integer> param = quickSortStack.pop();

            // 得到基准元素位置
            int pivotIndex = partition2(array, param.get("startIndex"), param.get("endIndex"));

            // 根据基准元素分成两部分，把每一部分的起止下标入栈
            if (param.get("startIndex") < pivotIndex - 1) {
                Map<String, Integer> leftParam = new HashMap<>();
                leftParam.put("startIndex", param.get("startIndex"));
                leftParam.put("endIndex", pivotIndex-1);
                quickSortStack.push(leftParam);
            }

            if(pivotIndex + 1 < param.get("endIndex")){
                Map<String, Integer> rightParam = new HashMap<>();
                rightParam.put("startIndex", pivotIndex + 1);
                rightParam.put("endIndex", param.get("endIndex"));
                quickSortStack.push(rightParam);
            }
        }
    }

    /**
     * 分治算法（双边循环）
     *
     * @param array      元数组
     * @param startIndex 起始下标
     * @param endIndex   结束下标
     * @return left
     */
    private static int partition(int[] array, int startIndex, int endIndex) {
        // 获取第一个元素作为基准元素
        int pivot = array[startIndex];

        int left = startIndex;
        int right = endIndex;

        while (left != right) {
            // 控制right比较并左移
            while (left < right && array[right] > pivot) {
                right--;
            }

            // 控制left比较并右移
            while (left < right && array[left] <= pivot) {
                left++;
            }

            // 交换right和left指针所指向的元素
            if (left < right) {
                int p = array[left];
                array[left] = array[right];
                array[right] = p;
            }
        }
        // pivot 和指针重合点交换
        array[startIndex] = array[left];
        array[left] = pivot;

        return left;
    }

    /**
     * 分治算法（单边循环）
     *
     * @param array      原数组
     * @param startIndex 起始下标
     * @param endIndex   结束下标
     * @return
     */
    public static int partition2(int[] array, int startIndex, int endIndex) {
        // 选取基准元素
        int pivot = array[startIndex];
        int mark = startIndex;
        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (array[i] < pivot) {
                mark++;
                int p = array[mark];
                array[mark] = array[i];
                array[i] = p;
            }
        }
        array[startIndex] = array[mark];
        array[mark] = pivot;
        return mark;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{4, 4, 6, 5, 3, 2, 8, 1};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

        int[] arr2 = new int[]{7, 9, 6, 5, 3, 2, 8, 1};
        quickSortNoRecursion(arr2, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr2));
    }

}
