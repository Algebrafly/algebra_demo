package com.algebra.demobase.conf.mq;

/**
 * @author al
 * @date 2020/2/20 13:54
 * @description
 */
public class RabbitMqConfig {

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

        System.out.println( 9 >> 1);

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
