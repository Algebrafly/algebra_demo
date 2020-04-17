package com.algebra.demo.util.algorithm;

/**
 * @author al
 * @date 2020/4/17 11:58
 * @description http://www.cppblog.com/tgh621/archive/2008/11/11/66595.html
 */
public class BitOprMatchString {


    private int reHash(int a, int b, int d, int h){
        return ((((h) - (a)*d) << 1) + (b));
    }

    public void kr(char[] x, int m, char[] y, int n){
        int d, hx, hy, i, j;

        /* Preprocessing */
        for (d = i = 1; i < m; ++i) {
            d = (d << 1);
        }

        for (hy = hx = i = 0; i < m; ++i) {
            hx = ((hx<<1) + x[i]);
            hy = ((hy<<1) + y[i]);
        }

        /* Searching */
        j = 0;
        while (j <= n-m) {
            if (hx == hy && memcmp(x, y, m) == 0) {
                System.out.println(j);
            }
            hy = reHash(y[j], y[j + m], d ,hy);
            ++j;
        }
    }

    private int memcmp(char[] x, char[] y, int m) {
        return 0;
    }





}
