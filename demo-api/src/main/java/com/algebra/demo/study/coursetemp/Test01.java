package com.algebra.demo.study.coursetemp;

import java.math.BigDecimal;

/**
 * @author al
 * @date 2020/5/26 17:23
 * @description 为了防止精度损失，禁止使用构造方法 BigDecimal(double)的方式把 double 值转化为 BigDecimal 对象。
 */
public class Test01 {

    public static void main(String[] args) {

        BigDecimal b = new BigDecimal(0.1f);
        System.out.println(b.toString());

        BigDecimal b2 = new BigDecimal("0.1");
        System.out.println(b2.toString());

    }

}
