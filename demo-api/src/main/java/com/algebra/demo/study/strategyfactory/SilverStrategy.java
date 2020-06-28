package com.algebra.demo.study.strategyfactory;

/**
 * @author al
 * @date 2020/1/19 9:53
 * @description 白银会员
 */
public class SilverStrategy implements SaleStrategy {
    @Override
    public double compute(double money) {
        System.out.println("白银会员 优惠50元");
        return money - 50;
    }

    @Override
    public String getType() {
        return UserType.SILVER_VIP.getCode();
    }
}
