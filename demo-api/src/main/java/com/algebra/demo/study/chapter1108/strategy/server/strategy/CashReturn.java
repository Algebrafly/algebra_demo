package com.algebra.demo.study.chapter1108.strategy.server.strategy;

import com.algebra.demo.study.chapter1108.strategy.server.CashStrategy;

import java.math.BigDecimal;

/**
 * @author al
 * @date 2020/11/18 10:23
 * @description 满减
 */
public class CashReturn implements CashStrategy {

    private BigDecimal condition;
    private BigDecimal moneyReturn;

    public CashReturn(BigDecimal condition, BigDecimal moneyReturn){
        this.condition = condition;
        this.moneyReturn = moneyReturn;
    }

    @Override
    public BigDecimal getCashResult(BigDecimal srcPrice) {
        BigDecimal result = new BigDecimal("0");
        if(srcPrice.compareTo(condition) >= 0){
            result = srcPrice.subtract(srcPrice.divide(condition,BigDecimal.ROUND_FLOOR).multiply(moneyReturn));
        }
        return result;
    }
}
