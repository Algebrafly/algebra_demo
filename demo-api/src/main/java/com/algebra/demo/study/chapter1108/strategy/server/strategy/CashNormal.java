package com.algebra.demo.study.chapter1108.strategy.server.strategy;

import com.algebra.demo.study.chapter1108.strategy.server.CashStrategy;

import java.math.BigDecimal;

/**
 * @author al
 * @date 2020/11/18 10:21
 * @description 原价
 */
public class CashNormal implements CashStrategy {
    @Override
    public BigDecimal getCashResult(BigDecimal srcPrice) {
        return srcPrice;
    }
}
