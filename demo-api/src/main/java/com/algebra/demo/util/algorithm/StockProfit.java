package com.algebra.demo.util.algorithm;

/**
 * @author al
 * @date 2020/10/26 10:07
 * @description [算法]模拟股票买卖，获取最大收益
 */
public class StockProfit {

    public static int maxProfitForOneTime(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }

    public int maxProfitForAnyTime(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) {
                maxProfit += prices[i] - prices[i-1];
            }
        }
        return maxProfit;
    }


    public static void main(String[] args) {
        int[] prices = {9, 2, 7, 4, 3, 1, 8, 4};
        System.out.println(maxProfitForOneTime(prices));
    }

}
