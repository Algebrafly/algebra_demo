package com.algebra.demo.study.reconstruction;

/**
 * @author al
 * @date 2020/5/19 15:53
 * @description 筛选法求N以内的所以素数
 * @verson 3.0 --> 3.1
 */
public class PrimesGeneratorV4 {

    private static boolean[] crossedOut;
    private static int[] result;

    public static int[] generatorPrimes(int maxValue) {
        if (maxValue < 2) {
            return new int[0];
        } else {
            // 初始化数据
            uncrossIntegersUpTo(maxValue);
            // 筛选法
            crossOutMultiples();
            // 加载数据
            putUncrossIntegerIntoResult();
            return result;
        }
    }

    public static void uncrossIntegersUpTo(int maxValue) {
        crossedOut = new boolean[maxValue + 1];
        // initialize array to true
        for (int i = 2; i < crossedOut.length; i++) {
            crossedOut[i] = false;
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
        double maxPrimesFactor = Math.sqrt(crossedOut.length) + 1;
        return (int) maxPrimesFactor;
    }

    private static boolean notCrossed(int i) {
        return !crossedOut[i];
    }

    private static void crossOutMultiplesOf(int i) {
        for (int multiple = 2 * i; multiple < crossedOut.length; multiple += i) {
            crossedOut[multiple] = true;
        }
    }

    // ------------------------------------------------------------------

    private static void putUncrossIntegerIntoResult() {
        result = new int[numberOfUncrossedIntegers()];
        for (int i = 2, j = 0; i < crossedOut.length; i++) {
            if (notCrossed(i)) {
                result[j++] = i;
            }
        }
    }

    private static int numberOfUncrossedIntegers() {
        int count = 0;
        for (int i = 2; i < crossedOut.length; i++) {
            if (notCrossed(i)) {
                count++;
            }
        }
        return count;
    }


    public static void main(String[] args) {

        for(int i = 2; i < 500; i++){
            verifyPrimesList(generatorPrimes(i));
        }

    }

    private static void verifyPrimesList(int[] list){
        for (int i : list) {
            verifyPrimes(i);
        }
    }

    private static void verifyPrimes(int n){
        for(int factor = 2; factor < n; factor ++){
            boolean b = n % factor != 0;
            System.err.println(b);
        }
    }


}
