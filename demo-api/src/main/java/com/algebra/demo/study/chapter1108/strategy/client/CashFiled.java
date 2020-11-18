package com.algebra.demo.study.chapter1108.strategy.client;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author al
 * @date 2020/11/18 10:40
 * @description 客户端条件域
 */
@Data
public class CashFiled {

    private String type;

    private BigDecimal rebate;

    private BigDecimal condition;

    private BigDecimal returnMoney;

}
