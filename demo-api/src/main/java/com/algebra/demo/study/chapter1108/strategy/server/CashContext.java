package com.algebra.demo.study.chapter1108.strategy.server;

import com.algebra.demo.study.chapter1108.strategy.client.CashFiled;
import com.algebra.demo.study.chapter1108.strategy.server.strategy.CashNormal;
import com.algebra.demo.study.chapter1108.strategy.server.strategy.CashRebate;
import com.algebra.demo.study.chapter1108.strategy.server.strategy.CashReturn;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author al
 * @date 2020/11/18 10:17
 * @description 简单工厂模式下的策略模式
 */
public class CashContext {

    private CashStrategy cashStrategy;

    public CashContext(CashFiled cashFiled) throws Exception {
        String type = cashFiled.getType();
        switch (type) {
            case "normal":
                cashStrategy = new CashNormal();
                break;
            case "rebate":
                cashStrategy = new CashRebate(cashFiled.getRebate());
                break;
            case "return":
                cashStrategy = new CashReturn(cashFiled.getCondition(),cashFiled.getReturnMoney());
                break;
            default:
                break;
        }
    }

    public BigDecimal getResult(BigDecimal price) {
        return cashStrategy.getCashResult(price).setScale(2, BigDecimal.ROUND_HALF_UP);
    }


}
