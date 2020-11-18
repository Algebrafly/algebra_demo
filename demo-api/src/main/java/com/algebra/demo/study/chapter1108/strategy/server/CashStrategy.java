package com.algebra.demo.study.chapter1108.strategy.server;

import java.math.BigDecimal;

/**
 * @author al
 * @date 2020/11/18 10:19
 * @description 收银策略
 */
public interface CashStrategy {

    /**
     * 获取收银金额
     * @param srcPrice 原金额
     * @return 实收金额
     */
    BigDecimal getCashResult(BigDecimal srcPrice);

}
