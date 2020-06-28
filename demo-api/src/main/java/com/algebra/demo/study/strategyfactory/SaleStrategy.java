package com.algebra.demo.study.strategyfactory;

/**
 * @author al
 * @date 2020/1/19 9:43
 * @description 策略模式接口
 */
public interface SaleStrategy {
    /**
     * 计算打折价格
     * @param money 原价
     * @return 折扣价
     */
    double compute(double money);

    /**
     * 获取type
     * @return type
     */
    String getType();
}
