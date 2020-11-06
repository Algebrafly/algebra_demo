package com.algebra.demo.util.interview;

import java.util.Arrays;

/**
 * @author al
 * @date 2020/11/6 15:41
 * @description 【小灰的算法之旅-java版实现】- 寻找全排列的下一个数
 * 找出这个正整数所有数字全排列的下一个数字（大于且仅大于原数字）
 */
public class NearestNumber {

    public static int[] findNearestNumber(int[] numbers) {
        // 1. 从后往前查看逆序区域，找到逆序区域的前一个位置，也就是数字置换的边界
        int index = findTransferPoint(numbers);
        if (index == 0) {
            // 如果数字置换边界是0，说明整个数组已经全部逆序，无法得到更大的数组
            return null;
        }

        // 2. 把逆序区域的前一位和逆序区域中刚刚大于他的数字交换位置
        int[] numberCopy = Arrays.copyOf(numbers, numbers.length);
        exchangeHead(numberCopy, index);

        // 3. 把原来的逆序区域转换成顺序区域
        reverse(numberCopy, index);


        return numberCopy;
    }

    /**
     * 反转逆序序列
     * 1 2 3 4 (9 8 7 6 5)  - 逆序为 9 8 7 6 5，head交换值为 5
     * 交换后 - 1 2 3 5 (9 8 7 6 4)
     * 9 <-> 4, 8 <-> 6
     *
     * @param numbers 数组
     * @param index   逆序位置
     * @return 数组
     */
    private static int[] reverse(int[] numbers, int index) {
        for (int i = index, j = numbers.length - 1; i < j; i++, j--) {
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }
        return numbers;
    }

    /**
     * 找index 右边大于head的最小的值
     *
     * @param numbers 数组
     * @param index   逆序位置
     * @return 数组
     */
    private static int[] exchangeHead(int[] numbers, int index) {
        int head = numbers[index - 1];
        for (int i = numbers.length - 1; i > 0; i--) {
            if (head < numbers[i]) {
                numbers[index - 1] = numbers[i];
                numbers[i] = head;
                break;
            }
        }
        return numbers;
    }

    /**
     * 获取逆序位置
     * ps.
     * 1 2 3 4 (9 8 7 6 5)  - 逆序为 9 8 7 6 5，head交换值为 5
     * 1 2 3 4 9 5 6 7 (8)  - 逆序为 8，虽然后面还有 （9 5），head交换值为 8
     * 1 2 3 4 9 8 6 (7 5)  - 逆序为 7 5，head交换值为 7
     * 1 2 3 6 （9 8 7 5 4） - 逆序为 9 8 7 5 4，head交换值为 7
     *
     * @param numbers 数组
     * @return 逆序开始位置
     */
    private static int findTransferPoint(int[] numbers) {
        for (int i = numbers.length - 1; i > 0; i--) {
            if (numbers[i] > numbers[i - 1]) {
                return i;
            }
        }
        return 0;
    }

    public static void printNumber(int[] numbers) {
        if (numbers == null) {
            return;
        }
        for (int number : numbers) {
            System.out.print(number);
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 9, 8, 7, 6, 5};
        int[] nearestNumber = findNearestNumber(numbers);
        printNumber(nearestNumber);
    }

}
