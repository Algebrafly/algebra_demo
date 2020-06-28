package com.algebra.demo.study.strategyfactory;

/**
 * @author al
 * @date 2020/1/19 9:44
 * @description 普通客户
 */
public class OrdinaryStrategy implements SaleStrategy {
    @Override
    public double compute(double money) {
        System.out.println("普通会员，不打折！");
        return money;
    }

    @Override
    public String getType() {
        return UserType.NORMAL.getCode();
    }
}
