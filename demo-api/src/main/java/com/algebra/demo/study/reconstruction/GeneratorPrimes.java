package com.algebra.demo.study.reconstruction;

import java.util.Arrays;

/**
 * @author al
 * @date 2020/5/19 15:53
 * @description 筛选法求N以内的所以素数
 * @verson 1.0
 */
public class GeneratorPrimes {

    /**
     * 判断是个数是否是素数
     *
     * @param num number
     * @return 是-否
     */
    public static boolean isPrimes(int num) {
        if (num <= 3) {
            return num > 1;
        }
        double sqrt = Math.sqrt(num) + 1;
        System.out.println(sqrt);
        for (int i = 2; i <= Math.sqrt(num) + 1; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int[] generatorPrimes(int maxValue) {
        if (maxValue >= 2) {

            int s = maxValue + 1;
            boolean[] f = new boolean[s];
            int i;

            // 1. initialize array to true
            for (i = 0; i < s; i++) {
                f[i] = true;
            }

            f[0] = f[1] = false;

            // 2. sieve (过滤)
            int j;
            for (i = 2; i < Math.sqrt(s) + 1; i++) {
                // 使用筛法求N以内的素数，从2开始，不断剔除2的倍数，然后从剩下的数字中，选择最小的数3（这个数一定会是素数），然后剔除所有3的倍数，
                // 依次类推，最后剩下的数就全是素数了。
                for (j = 2 * i; j < s; j += i) {
                    f[j] = false;
                }
            }

            // 3. how many primes are there?
            int count = 0;
            for (i = 0; i < s; i++) {
                if (f[i]) {
                    count++;
                }
            }

            // 4. move the primes into the result
            int[] primes = new int[count];
            for (i = 0, j = 0; i < s; i++) {
                if (f[i]) {
                    primes[j++] = i;
                }
            }
            return primes;
        } else {
            return new int[0];
        }
    }


    public static void main(String[] args) {

        int[] nullValue = generatorPrimes(1);
        System.out.println(Arrays.toString(nullValue));

        int[] minArray = generatorPrimes(2);
        System.out.println(Arrays.toString(minArray));


        int[] threeArray = generatorPrimes(3);
        System.out.println(Arrays.toString(threeArray));


        int[] centArray = generatorPrimes(100);
        System.out.println(Arrays.toString(centArray));


        System.out.println("------------------------------------------");
        boolean primes = isPrimes(98);
        System.out.println(primes);

    }

}
