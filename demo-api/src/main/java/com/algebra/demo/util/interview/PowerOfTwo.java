package com.algebra.demo.util.interview;

/**
 * @author al
 * @date 2020/11/6 10:29
 * @description 【小灰的算法之旅-java版实现】- 判断一个数是否是2的整次幂
 */
public class PowerOfTwo {

    public static boolean isPowerOfTwo(int num) {
        int temp = 1;
        while (temp <= num) {
            if (temp == num) {
                return true;
            }
            temp = temp << 1;
        }
        return false;
    }

    public static boolean isPowerOfTwoV2(int num) {
        return ((num) & (num - 1)) == 0;
    }

    public static void main(String[] args) {

        System.out.println(isPowerOfTwoV2(88));

        System.out.println(isPowerOfTwoV2(64));
    }


}
