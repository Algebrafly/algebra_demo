package com.algebra.demo.study.chapter1108.factory01;

import java.math.BigDecimal;

/**
 * @author al
 * @date 2020/11/18 10:21
 * @description 原价
 */
public class CashNormal extends CashSuper {
    @Override
    public BigDecimal getCashResult(BigDecimal srcPrice) {
        return srcPrice;
    }
}
