package com.algebra.demo.util.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author al
 * @date 2020/11/20 11:02
 * @description
 */
public class LeetCodeUtility {


    public static void main(String[] args) {

        System.out.println(fastPow(3, 3));
        System.out.println("-------------------------------");
        System.out.println(fastPow(3, 4));
        System.out.println("-------------------------------");
        System.out.println(fastPow(3, 5));
        System.out.println("-------------------------------");
        System.out.println(fastPow(3, 7));
        System.out.println("-------------------------------");
        System.out.println(fastPow(3, 10));

        System.out.println("下面是矩阵快速幂");
        int[][] arr = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        int[][] arr1 = multiplyPower(arr, 3);
        System.out.println(Arrays.deepToString(arr1));

        System.out.println("下面是素数操作");
        System.out.println(judgePrime(22));
        System.out.println(judgePrimeV2(22));

        int[] primes = getPrimes(20);
        System.out.println(Arrays.toString(primes));

        System.out.println("质因数分解");
        primeFactor(100);

    }

    public static int fastPowNormal(int a, int x) {
        int base = a, ans = 1;
        int i = 1;
        while (x != 0) {
            if (x % 2 != 0) {
                // 奇数
                ans *= base;
                System.out.println("第" + i + "次 ans = " + ans);
            }
            base *= base;
            System.out.println("第" + i + "次 base = " + base);
            x /= 2;
            System.out.println("第" + i + "次 x = " + x);
            i++;
        }
        return ans;
    }

    /**
     * 快速幂，求解：a^x
     *
     * @param a 基底
     * @param x 指数
     * @return 值
     */
    public static int fastPow(int a, int x) {
        int base = a, ans = 1;
        while (x != 0) {
            if ((1 & x) != 0) {
                // 末尾一位是0还是1
                ans *= base;
            }
            base *= base;
            x >>= 1;
        }
        return ans;
    }

    public static int[][] multiply(int[][] a, int[][] b) {
        int[][] arr = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    arr[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return arr;
    }

    /**
     * 矩阵快速幂，求解矩阵 a^n
     *
     * @param a 矩阵
     * @param n 幂
     * @return 值
     */
    public static int[][] multiplyPower(int[][] a, int n) {
        int[][] res = new int[a.length][a[0].length];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                if (i == j) {
                    res[i][j] = 1;
                } else {
                    res[i][j] = 0;
                }
            }

        }
        while (n != 0) {
            if ((n & 1) == 1) {
                res = multiply(res, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return res;
    }

    /**
     * 判断一个数n是否是素数
     *
     * @param n number
     * @return boolean
     */
    public static boolean judgePrime(int n) {
        for (int i = 2; i * i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean judgePrimeV2(int n) {
        for (int i = 2; i < Math.sqrt(n) + 1; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 求 N 以内的质数集合
     *
     * @param n 数字范围
     * @return 质数集合
     */
    public static int[] getPrimes(int n) {
        // 初始化数组，值全部为0
        int[] vis = new int[n + 1];

        if (n >= 2) {
            vis[0] = vis[1] = 1;

            for (int i = 2; i < Math.sqrt(n) + 1; i++) {
                if (vis[i] == 0) {
                    for (int j = 2 * i; j <= n; j += i) {
                        vis[j] = 1;
                    }
                }
            }

            // 下面组装输出输出
            int count = 0;
            for (int vi : vis) {
                if (vi == 0) {
                    count++;
                }
            }
            int[] rst = new int[count];
            int i = 0;
            for (int j = 0; j < vis.length; j++) {
                if (vis[j] == 0) {
                    rst[i++] = j;
                }
            }
            return rst;
        } else {
            return new int[0];
        }
    }


    /**
     * 质因数分解
     * 根据「唯一分解定理」，任何一个大于 1 的正整数都能唯一分解为有限个质数的乘积: N = p1^e1*p2^e2 ... pm^em ,其中  均为正整数，而  均为质数
     *
     * @param n 被分解的数
     */
    public static void primeFactor(int n) {
        int number = n;
        List<Integer> prime = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();

        for (int i = 2; i <= Math.sqrt(n) + 1; i++) {
            if (n % i == 0) {
                prime.add(i);
                int count = 0;
                while (n % i == 0) {
                    count++;
                    n /= i;
                }
                counts.add(count);
            }
        }
        if (n > 1) {
            prime.add(n);
            counts.add(1);
        }
        System.out.println(prime.toString());
        System.out.println(counts.toString());

        // 下面组装因式格式化输出
        StringBuilder sb = new StringBuilder();
        sb.append(number).append("=");
        for (int i = 0; i < prime.size(); i++) {
            sb.append(prime.get(i)).append("^").append(counts.get(i));
            if (prime.size() - 1 != i) {
                sb.append("*");
            }
        }
        System.out.println(sb.toString());
    }


    /**
     * 求解最大公约数（欧几里得算法）
     * 【裴蜀定理】
     * 若 a,b,x,y,m 是整数，则 ax + by = m 有解当且仅当 m 是 gcd(a,b) 的倍数。
     * 该定理有一个重要的推论：整数 a,b 互质的充要条件是存在整数 x,y 使 ax + by = 1 。
     *
     * @param a 数a
     * @param b 数b
     * @return
     */
    public static int greatestCommonDivisor(int a, int b) {
        return b == 0 ? a : greatestCommonDivisor(b, a % b);
    }


}
