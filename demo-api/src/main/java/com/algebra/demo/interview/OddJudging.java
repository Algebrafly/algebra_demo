package com.algebra.demo.interview;

/**
 * @author al
 * @date 2020/2/25 15:37
 * @description
 */
public class OddJudging {

    /**
     * 判断一个数是否是奇数
     * @param i 数字
     * @return boolean
     */
    public static boolean isOdd(Integer i){
//        if(i % 2 == 0){
//            return false;
//        } else {
//            return true;
//        }
//        return i % 2 == 1;

        return i >> 1 << 1 != i;
//        return (i & 1) == 1;
    }

    public static void main(String[] args) {

        System.out.println(isOdd(14));

        // 高位补0，右移1位
        System.out.println( 17 >> 1);

        // 高位补1，右移1位
        System.out.println( -17 >> 1);

        // 高位补0,（无符号）右移1位
        System.out.println( 17 >>> 1);

        // 高位补0,（无符号）右移1位  （32位二进制要写全）
        System.out.println( -17 >>> 1);

        // 两个操作数中位都为1，结果才为1，否则结果为0
//        1010
//        0001 &
//       --------
//        0000

        // 两个位只要有一个为1，那么结果就是1，否则就为0
//        1010
//        1001 |
//       --------
//        1011

        // 两个操作数的位中，相同则结果为0，不同则结果为1
//        1010
//        1001 ^
//       --------
//        0011

        // 如果位为0，结果是1，如果位为1，结果是0
//        1010 ~
//       --------
//        0101


        // 移位运算

    }

}
