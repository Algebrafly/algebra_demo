package com.algebra.demo.util.interview;

/**
 * @author al
 * @date 2020/11/9 18:00
 * @description 【小灰的算法之旅-java版实现】- 找到两个数组的中位数
 * 两个有序数组合并后，找出合并数组的中位数
 * 二分法查找和调整位置
 */
public class MedianSorted {

    public static String findMedianSortedArrays(int[] aryOne, int[] aryTwo) throws Exception {

        // 如果数组A的长度大于数组B的长度则交换数组（避免后续计算j值之后越界，且减少了i的搜索次数）
        int m = aryOne.length;
        int n = aryTwo.length;
        if (m > n) {
            int[] aryTemp = aryOne;
            aryOne = aryTwo;
            aryTwo = aryTemp;

            int temp = m;
            m = n;
            n = temp;
        }

        if (n == 0) {
            throw new Exception("ValueError");
        }

        int start = 0;
        int end = m;
        int halfLen = (m + n + 1) / 2;

        while (start <= end) {
            /* TODO 【VIP】 二分法确定i值，公式计算j值：i+j = (m+n+1)/2 */
            int i = (start + end) / 2;
            int j = halfLen - i;

            if (i < m && aryTwo[j - 1] > aryOne[i]) {
                // i 偏小了，需要右移
                start = i + 1;
            } else if (i > 0 && aryOne[i - 1] > aryTwo[j]) {
                // i 偏大了，需要左移
                end = i - 1;
            } else {
                int maxOfLeft;
                // i 刚好合适，或者已经达到数组边界
                if (i == 0) {
                    // 数组A里面的最小数，都大于数组B里面的最大数
                    // 包含以下情景：a= {7,8,9} b = {1,2,3},  数组长度相同，且 B 中 所有元素都小于 A 中所有元素
                    maxOfLeft = aryTwo[j - 1];
                } else if (j == 0) {
                    // a = {1,2,3} b= {7,8,9}
                    // 数组长度相同，且 A 中 所有元素都小于 B中所有元素
                    maxOfLeft = aryOne[i - 1];
                } else {
                    // 数组A里面的最大数，都小于数组B里面的最小数
                    // a = {1,2,3} b= {7,8,9,10}, A长度 < B长度
                    maxOfLeft = Math.max(aryOne[i - 1], aryTwo[j - 1]);
                }

                if ((m + n) % 2 == 1) {
                    // 如果大数组的长度是奇数，中位数就是左半部分的最大值
                    return String.valueOf(maxOfLeft);
                }
                int minOfRight;
                if (i == m) {
                    minOfRight = aryTwo[j];
                } else if (j == n) {
                    minOfRight = aryOne[i];
                } else {
                    minOfRight = Math.min(aryOne[i], aryTwo[j]);
                }
                // 如果大数组的长度是偶数，取左侧最大值和右侧最小值的平均值
                return String.valueOf((maxOfLeft + minOfRight) / 2.0);
            }

        }
        return null;
    }


    public static void main(String[] args) throws Exception {

        int[] a = new int[]{10, 11, 12};

        int[] b = new int[]{1, 2, 3};

        System.out.println(findMedianSortedArrays(b, a));

    }

}
