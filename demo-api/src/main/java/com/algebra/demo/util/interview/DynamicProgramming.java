package com.algebra.demo.util.interview;

import java.util.Arrays;

/**
 * @author al
 * @date 2020/11/10 11:11
 * @description 【小灰的算法之旅-java版实现】- 动态规划问题：1.金矿 2.背包
 * 将复杂问题转化成子问题：1.确定问题边界；2.确定状态转移方程
 */
public class DynamicProgramming {

    /**
     * 递归实现-金矿最优收益问题
     * 状态转移方程：F[n,w] = Max{F(n-1,w), F(n-1,w-p[n-1])+g[n-1]}
     * 边界值：F(0,0) = 0; F(0,1) = 0; ... ; F(1,5) = 400; F(2,5) = 500;
     *
     * @param w 工人总数
     * @param n 金矿总数
     * @param p 开采每个金矿需要的人数
     * @param g 金矿含金量
     * @return 最优收益
     */
    public static int getBestGoldMining(int w, int n, int[] p, int[] g) {
        if (w == 0 || n == 0) {
            return 0;
        }
        if (w < p[n - 1]) {
            return getBestGoldMining(w, n - 1, p, g);
        }
        return Math.max(getBestGoldMining(w, n - 1, p, g), getBestGoldMining(w - p[n - 1], n - 1, p, g) + g[n - 1]);
    }


    /**
     * 自底向上求解
     * 时间复杂度&空间复杂度：O(nw)
     *
     * @param w 工人数
     * @param p 金矿开采需要的工人数
     * @param g 金矿储量
     * @return 最优收益
     */
    public static int getBestGoldMiningV2(int w, int[] p, int[] g) {
        // 创建二维表格：行-金矿数量，列-开矿工人数
        int[][] resultTable = new int[g.length + 1][w + 1];

        // 填充表格
        for (int i = 1; i <= g.length; i++) {
            for (int j = 1; j <= w; j++) {
                if (j < p[i - 1]) {
                    resultTable[i][j] = resultTable[i - 1][j];
                } else {
                    // w >= p[n-1]
                    resultTable[i][j] = Math.max(resultTable[i - 1][j], resultTable[i - 1][j - p[i - 1]] + g[i - 1]);
                }
            }
        }

        System.out.println(Arrays.deepToString(resultTable));

        return resultTable[g.length][w];
    }

    /**
     * 自底向上求解(优化空间结构)
     * 时间复杂度：O(nw)
     * 空间复杂度：O(n)
     *
     * @param w 工人数
     * @param p 金矿开采需要的工人数
     * @param g 金矿储量
     * @return 最优收益
     */
    public static int getBestGoldMiningV3(int w, int[] p, int[] g) {

        // 当前结果集
        int[] results = new int[w+1];

        // 填充一维数组
        for (int i = 1; i <= g.length; i++) {
            for (int j = w; j >= 1; j--) {
                if(j >= p[i-1]){
                    results[j] = Math.max(results[j], results[j-p[i-1]] + g[i-1]);
                }
            }
        }
        System.out.println(Arrays.toString(results));

        return results[w];
    }


    public static void main(String[] args) {
        int w = 10;
        int[] p = {5, 5, 3, 4, 3};
        int[] g = {400, 500, 200, 300, 350};
//        System.out.println("最优收益：" + getBestGoldMiningV2(w, p, g));
        System.out.println("最优收益：" + getBestGoldMiningV3(w, p, g));

    }

}
