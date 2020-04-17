package com.algebra.demo.util.algorithm;

/**
 * @author al
 * @date 2020/4/17 11:58
 * @description http://www.cppblog.com/tgh621/archive/2008/11/11/66595.html
 */
public class BitOprMatchString2 {

    private static final int SIZE = 256;

    private static final int INT_SIZE = 4*8;

    public static int preSo(char[] x, int m, int[] s) {
        int j, lim;
        int i;

        for (i = 0; i < SIZE; ++i) {
            s[i] = ~0;
        }

        for (lim = i = 0, j = 1; i < m; ++i, j<<=1) {
            s[x[i]] &= ~j;
            lim |= j;
        }
        lim = ~(lim>>1);

        return lim;
    }

    public static void so(char[] x, int m, char[] y, int n) {
        int lim, state;
        int[] s = new int[SIZE];
        int j;

        if(m > INT_SIZE){
            System.err.println("SO: Use pattern size <= word size");
        }

        /* Preprocessing */
        lim = preSo(x, m, s);

        /* Searching */
        for (state = ~0, j = 0; j < n; ++j) {
            state = (state<<1) | s[y[j]];
            if (state < lim) {
                System.out.println(j - m + 1);
            }
        }

    }


    public static void main(String[] args) {

        String s1 = "asdasdwwwe";
        String s2 = "we";

        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();

        so(cs1,cs1.length,cs2,cs2.length);

    }



}
