package com.algebra.demo.study.strategyfactory;

/**
 * @author al
 * @date 2020/1/19 9:55
 * @description 白金会员
 */
public class PlatinumStrategy implements SaleStrategy {
    @Override
    public double compute(double money) {
        System.out.println("白金会员 优惠50元，再打7折");
        return (money - 50) * 0.7;
    }

    @Override
    public String getType() {
        return UserType.PLATINUM_VIP.getCode();
    }
}
