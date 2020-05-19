package com.algebra.demo.study;

import java.util.Arrays;

/**
 * @author al
 * @date 2020/5/19 15:53
 * @description 筛选法求N以内的所以素数
 * @verson 3.0 --> 3.1
 */
public class PrimesGeneratorV3 {

    private static boolean[] isCrossed;
    private static int[] result;

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

    public static void initializeArrayOfInteger(int maxValue) {
        isCrossed = new boolean[maxValue + 1];
        // initialize array to true
        for (int i = 2; i < isCrossed.length; i++) {
            isCrossed[i] = false;
        }
//        isCrossed[0] = isCrossed[1] = true;
    }

    // ------------------------------------------------------------------

    public static void crossOutMultiples() {
        int maxPrimesFactor = calcMaxPrimesFactor();
        for (int i = 2; i <= maxPrimesFactor; i++) {
            if (notCrossed(i)) {
                crossOutMultiplesOf(i);
            }
        }
    }

    private static int calcMaxPrimesFactor() {
        double maxPrimesFactor = Math.sqrt(isCrossed.length) + 1;
        return (int) maxPrimesFactor;
    }

    private static boolean notCrossed(int i) {
        return !isCrossed[i];
    }

    private static void crossOutMultiplesOf(int i) {
        for (int multiple = 2 * i; multiple < isCrossed.length; multiple += i) {
            isCrossed[multiple] = true;
        }
    }

    // ------------------------------------------------------------------

    private static void putUncrossIntegerIntoResult() {
        result = new int[numberOfUncrossedIntegers()];
        for (int i = 2, j = 0; i < isCrossed.length; i++) {
            if (notCrossed(i)) {
                result[j++] = i;
            }
        }
    }

    private static int numberOfUncrossedIntegers() {
        int count = 0;
        for (int i = 2; i < isCrossed.length; i++) {
            if (notCrossed(i)) {
                count++;
            }
        }
        return count;
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
