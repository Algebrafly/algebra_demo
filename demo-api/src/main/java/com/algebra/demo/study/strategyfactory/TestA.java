package com.algebra.demo.study.strategyfactory;

/**
 * @author al
 * @date 2020/1/19 10:04
 * @description 测试
 */
public class TestA {
    private static double getResult(double money, String type) throws Exception {
        if(money < 1000){
            return money;
        }
        SaleStrategy strategy = StrategyFactory.getInstance().get(type);

        if(strategy == null){
            System.out.println("please input right type");
            throw new Exception("strategy is not exit, please input right type");
        }
        return strategy.compute(money);
    }

    public static void main(String[] args) throws Exception {

        double result = getResult(1001, "1");
        System.out.println("实付价格："+result);

    }
}
