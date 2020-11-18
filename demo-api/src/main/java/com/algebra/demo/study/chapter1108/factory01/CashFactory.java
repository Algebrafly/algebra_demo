package com.algebra.demo.study.chapter1108.factory01;

import com.algebra.demo.study.chapter1108.strategy.client.CashFiled;

/**
 * @author al
 * @date 2020/11/18 11:35
 * @description
 */
public class CashFactory {

    public static CashSuper createCashAccept(CashFiled cashFiled) throws Exception {
        CashSuper cs = null;
        String type = cashFiled.getType();
        switch (type) {
            case "normal":
                cs = new CashNormal();
                break;
            case "rebate":
                cs = new CashRebate(cashFiled.getRebate());
                break;
            case "return":
                cs = new CashReturn(cashFiled.getCondition(),cashFiled.getReturnMoney());
                break;
            default:
                break;
        }
        return cs;
    }

}
