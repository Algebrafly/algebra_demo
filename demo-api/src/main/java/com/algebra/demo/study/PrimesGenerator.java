package com.algebra.demo.study;

import java.util.Arrays;

/**
 * @author al
 * @date 2020/5/19 15:53
 * @description 筛选法求N以内的所以素数
 * @verson 2.0
 */
public class PrimesGenerator {

    private static boolean[] f;
    private static int[] primes;
    private static int s;

    public static int[] generatorPrimes(int maxValue){
        if(maxValue < 2){
            return new int[0];
        } else {
            // 初始化数据
            initializeSieve(maxValue);
            // 筛选法
            sieve();
            // 加载数据
            loadPrimes();
            return primes;
        }
    }

    public static void initializeSieve(int maxValue){
        s = maxValue + 1;
        f = new boolean[s];
        int i;

        // initialize array to true
        for (i = 0; i < s; i++) {
            f[i] = true;
        }

        f[0] = f[1] = false;
    }

    public static void sieve(){
        // sieve (过滤)
        int i;
        int j;
        for (i = 2; i < Math.sqrt(s) + 1; i++) {
            // 使用筛法求N以内的素数，从2开始，不断剔除2的倍数，然后从剩下的数字中，选择最小的数3（这个数一定会是素数），然后剔除所有3的倍数，
            // 依次类推，最后剩下的数就全是素数了。
            for (j = 2 * i; j < s; j += i) {
                f[j] = false;
            }
        }
    }


    private static void loadPrimes(){
        int i;
        int j;

        // how many primes are there?
        int count = 0;
        for (i = 0; i < s; i++) {
            if (f[i]) {
                count++;
            }
        }

        // move the primes into the result
        primes = new int[count];
        for (i = 0, j = 0; i < s; i++) {
            if (f[i]) {
                primes[j++] = i;
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
