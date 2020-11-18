package com.algebra.demo.study.chapter1108.strategy.server.strategy;

import com.algebra.demo.study.chapter1108.strategy.server.CashStrategy;

import java.math.BigDecimal;

/**
 * @author al
 * @date 2020/11/18 10:31
 * @description 折扣
 */
public class CashRebate implements CashStrategy {

    private BigDecimal rebate;

    public CashRebate(BigDecimal rebate) throws Exception {
        if(rebate.compareTo(new BigDecimal(0)) < 0 || rebate.compareTo(new BigDecimal(1)) > 0){
            throw new Exception("折扣范围值：0 ~ 1");
        }
        this.rebate = rebate;
    }

    @Override
    public BigDecimal getCashResult(BigDecimal srcPrice) {
        return srcPrice.multiply(rebate);
    }
}
