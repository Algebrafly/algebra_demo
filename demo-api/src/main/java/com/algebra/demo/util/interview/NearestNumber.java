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

    private static int[] reverse(int[] numbers, int index) {
        for (int i = index, j = numbers.length - 1; i < j; i++, j++) {
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }
        return numbers;
    }

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

    private static int findTransferPoint(int[] numbers) {
        for (int i = numbers.length - 1; i > 0; i--) {
            if (numbers[i] > numbers[i - 1]) {
                return i;
            }
        }
        return 0;
    }

    public static void printNumber(int[] numbers){
        if(numbers == null){
            return;
        }
        for (int number : numbers) {
            System.out.print(number);
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        int[] nearestNumber = findNearestNumber(numbers);
        printNumber(nearestNumber);
    }

}
