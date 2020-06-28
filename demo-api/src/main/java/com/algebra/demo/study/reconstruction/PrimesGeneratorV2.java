package com.algebra.demo.study.reconstruction;

import java.util.Arrays;

/**
 * @author al
 * @date 2020/5/19 15:53
 * @description 筛选法求N以内的所以素数 --> 拆解方法 & 改方法名和缩减变量
 * @verson 2.0 --> 2.1
 */
public class PrimesGeneratorV2 {

    private static boolean[] isCrossed;
    private static int[] result;

    /**
     * 主方法
     * @param maxValue 最大限定值
     * @return 素数集合
     */
    public static int[] generatorPrimes(int maxValue) {
        if (maxValue < 2) {
            return new int[0];
        } else {
            // 初始化数据
            initializeArrayOfInteger(maxValue);
            // 筛选法
            crossOutMultiples();
            // 加载数据
            putUncrossIntegerIntoResult();
            return result;
        }
    }

    /**
     * 初始化素数集合
     * @param maxValue 界限值
     */
    public static void initializeArrayOfInteger(int maxValue) {
        isCrossed = new boolean[maxValue + 1];
        int i;
        isCrossed[0] = isCrossed[1] = false;
        // initialize array to true
        for (i = 2; i < isCrossed.length; i++) {
            isCrossed[i] = true;
        }

    }


    /**
     * 筛选素数
     */
    public static void crossOutMultiples() {
        // sieve (过滤)
        for (int i = 2; i < Math.sqrt(isCrossed.length) + 1; i++) {
            // 使用筛法求N以内的素数，从2开始，不断剔除2的倍数，然后从剩下的数字中，选择最小的数3（这个数一定会是素数），然后剔除所有3的倍数，
            // 依次类推，最后剩下的数就全是素数了。
            for (int j = 2 * i; j < isCrossed.length; j += i) {
                isCrossed[j] = false;
            }
        }
    }


    /**
     * 输出结果
     */
    private static void putUncrossIntegerIntoResult() {
        int i;
        int j;

        // how many primes are there?
        int count = 0;
        for (i = 0; i < isCrossed.length; i++) {
            if (isCrossed[i]) {
                count++;
            }
        }

        // move the primes into the result
        result = new int[count];
        for (i = 0, j = 0; i < isCrossed.length; i++) {
            if (isCrossed[i]) {
                result[j++] = i;
            }
        }
    }


    public static void main(String[] args) {

        int[] nullValue = generatorPrimes(0);
        System.out.println(Arrays.toString(nullValue));

        int[] minArray = generatorPrimes(1);
        System.out.println(Arrays.toString(minArray));


        int[] threeArray = generatorPrimes(3);
        System.out.println(Arrays.toString(threeArray));


        int[] centArray = generatorPrimes(100);
        System.out.println(Arrays.toString(centArray));


    }


}
