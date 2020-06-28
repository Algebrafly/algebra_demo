package com.algebra.demo.study.strategyfactory;

/**
 * @author al
 * @date 2020/1/19 9:55
 * @description 黄金会员
 */
public class GoldStrategy implements SaleStrategy {
    @Override
    public double compute(double money) {
        System.out.println("黄金会员 8折");
        return money * 0.8;
    }

    @Override
    public String getType() {
        return UserType.GOLD_VIP.getCode();
    }
}
