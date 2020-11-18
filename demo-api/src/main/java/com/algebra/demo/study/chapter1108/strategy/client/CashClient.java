package com.algebra.demo.study.chapter1108.strategy.client;

import com.algebra.demo.study.chapter1108.strategy.server.CashContext;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @author al
 * @date 2020/11/18 10:17
 * @description 收银客户端
 */
public class CashClient {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            CashFiled cashFiled = new CashFiled();

            System.out.println("请输入商品价格：");
            String price = scanner.next();
            System.out.println("请输入优惠策略（normal-无优惠，rebate-打折，return-满减）：");
            String type = scanner.next();
            cashFiled.setType(type);
            if ("rebate".equals(type)) {
                System.out.println("请输入折扣率（范围在0~1之间）：");
                String rebate = scanner.next();
                cashFiled.setRebate(new BigDecimal(rebate));
            } else if ("return".equals(type)) {
                System.out.println("请输入满减条件金额：");
                String condition = scanner.next();
                System.out.println("请输入满减金额：");
                String moneyReturn = scanner.next();
                cashFiled.setCondition(new BigDecimal(condition));
                cashFiled.setReturnMoney(new BigDecimal(moneyReturn));
            }

            CashContext cashContext = new CashContext(cashFiled);
            BigDecimal result = cashContext.getResult(new BigDecimal(price));
            System.out.println("实收金额：" + result.toString());


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

}
