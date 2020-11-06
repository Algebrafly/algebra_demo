package com.algebra.demo.util.interview;

/**
 * @author al
 * @date 2020/11/6 9:19
 * @description 【小灰的算法之旅-java版实现】- 求最大公约数
 */
public class GreatestCommonDivisor {

    /**
     * 暴力遍历
     *
     * @param a number a
     * @param b number b
     * @return GreatestCommonDivisor
     */
    public static int greatestCommonDivisorV1(int a, int b) {
        int big = Math.max(a, b);
        int small = Math.min(a, b);

        if (big % small == 0) {
            return small;
        }
        for (int i = small / 2; i > 1; i--) {
            if (small % i == 0 && big % i == 0) {
                return i;
            }
        }
        return 1;
    }

    /**
     * 辗转相除法
     * 定理：两个正整数a和b（a>b），他们的最大公约数等于a除以b的余数c，和b之间的最大公约数
     *
     * @param a number a
     * @param b number b
     * @return GreatestCommonDivisor
     */
    public static int greatestCommonDivisorV2(int a, int b) {
        int big = Math.max(a, b);
        int small = Math.min(a, b);

        if (big % small == 0) {
            return small;
        }
        return greatestCommonDivisorV2(big % small, small);
    }


    /**
     * 更相减损术
     * 定理：两个正整数a和b（a>b），他们的最大公约数等于a-b的差值c，和较小数b的最大公约数
     *
     * @param a number a
     * @param b number b
     * @return GreatestCommonDivisor
     */
    public static int greatestCommonDivisorV3(int a, int b) {
        if (a == b) {
            return a;
        }
        int big = Math.max(a, b);
        int small = Math.min(a, b);
        return greatestCommonDivisorV2(big - small, small);
    }

    /**
     * 更相减损术 & 移位运算：
     * 当a和b均为偶数时，gcd(a,b) = 2×gcd(a/2, b/2) = 2×gcd(a>>1,b>>1)
     * 当a为偶数，b为奇数时，gcd(a,b) = gcd(a/2,b) = gcd(a>>1,b)
     * 当a为奇数，b为偶数时，gcd(a,b) = gcd(a,b/2) = gcd(a,b>>1)
     * 当a和b均为奇数时，先利用更相减损术运算一次，gcd(a,b) = gcd(b,a- b)，此时a-b必然是偶数，然后又可以继续进行移位运算
     *
     * ps. 移位运算
     * （a & 1） == 0  --> 判断a是否是偶数（偶数的二进制末尾一定是 0）
     *     110
     *   & 001
     *   ------
     *     000
     *
     *  （奇数）>> 1 = (奇数-1) >> 1
     *
     *  快速求解一个数的二进制数：
     *  比如，29  -> 28+1 -> 14*2+1 -> 7*2*2+1 -> (3*2+1)*2*2+1
     *       11101        <- 11100+1<- 1110*2+1<- (110+1)*2*2+1
     *
     * @param a number a
     * @param b number b
     * @return GreatestCommonDivisor
     */
    public static int greatestCommonDivisorV4(int a, int b) {
        if (a == b) {
            return a;
        }
        if ((a & 1) == 0 && (b & 1) == 0) {
            return greatestCommonDivisorV4(a >> 1, b >> 1) << 1;
        } else if ((a & 1) == 0 && (b & 1) != 0) {
            return greatestCommonDivisorV4(a >> 1, b);
        } else if ((a & 1) != 0 && (b & 1) == 0) {
            return greatestCommonDivisorV4(a, b >> 1);
        } else {
            // 更相减损术
            int big = Math.max(a, b);
            int small = Math.min(a, b);
            return greatestCommonDivisorV2(big - small, small);
        }
    }


    public static void main(String[] args) {

        int a = 1001;
        int b = 15;

        System.out.println(greatestCommonDivisorV4(a, b));
    }

}
