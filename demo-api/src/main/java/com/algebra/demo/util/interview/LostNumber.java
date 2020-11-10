package com.algebra.demo.util.interview;

/**
 * @author al
 * @date 2020/11/10 17:15
 * @description 【小灰的算法之旅-java版实现】- 寻找消失的整数
 * 1. 在一个无序数组里有99个不重复的正整数，范围是1～100，唯独缺少1 个1～100中的整数。如何找出这个缺失的整数？ （1-100求和，然后依次减去数组值）
 * 2. 一个无序数组里有若干个正整数，范围是1～100，其中99个整数都出现 了偶数次 ，只有1个整数出现了奇数次 ，如何找到这个出现奇数次的整数？
 * 数组元素依次进行异或运算，最后剩下的值就是出现奇数次的整数
 * 3. 假设一个无序数组里有若干个正整数，范围是1～100，其中有98个整数 出现了偶数次，只有2个 整数出现了奇数次，如何找到这2个出现奇数 次的整数？
 * 分治之后，各个部分运用（2）的思路
 */
public class LostNumber {

    public static int[] findLostNum(int[] array) {
        // 用于存储出现奇数次的两个数
        int[] result = new int[2];
        // 1. 第一次进行整体异或运算
        int xorResult = 0;
        for (int i = 0; i < array.length; i++) {
            xorResult ^= array[i];
        }
        if (xorResult == 0) {
            //如果进行异或运算的结果为0，则说明输入的数组不符合题目要求
            return null;
        }
        // 确定2个整数的不同位，以此来做分组
        int separator = 1;
        // separator每次左移一位，当遇到xorResult中的1后，跳出循环
        while (0 == (xorResult & separator)) {
            // separator = separator << 1
            separator <<= 1;
        }
        System.out.println("separator二进制："+Integer.toBinaryString(separator));

        // 2.第2次分组进行异或运算
        for (int i = 0; i < array.length; i++) {
            if (0 == (array[i] & separator)) {
                result[0] ^= array[i];
            } else {
                result[1] ^= array[i];
            }
        }

        return result;
    }


    public static void main(String[] args) {

        int[] array = {4, 1, 2, 2, 5, 1, 4, 3};
        int[] result = findLostNum(array);
        System.out.println(result[0] + "," + result[1]);

    }


}
