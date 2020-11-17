package com.algebra.demo.util.interview;

import java.util.Arrays;

/**
 * @author al
 * @date 2020/11/10 11:11
 * @description 【小灰的算法之旅-java版实现】- 动态规划问题：1.金矿 2.背包
 * w个工人，开采n个黄金矿，每个矿的黄金含量不一样，需要的人力也不一样，怎样分配人力开采能获取最大黄金量？
 * 将复杂问题转化成子问题：1.确定问题边界；2.确定状态转移方程
 * <p>
 * 参考（动态规划经典问题）：
 * https://blog.csdn.net/program_developer/article/details/85274825
 * https://www.cnblogs.com/cruelty_angel/p/10576617.html
 * https://www.jianshu.com/p/866c0dd53619
 * https://www.cnblogs.com/bokeyuan-dlam/articles/9453128.html
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
     * 背包问题
     * 时间复杂度和空间复杂度：O(capacity*n)
     * 数学模型：
     * 1）MaxValue(i, j) = MaxValue(i-1, j) , j < size(i-1)
     * 2) MaxValue(i, j) = Max{MaxValue(i-1, j), MaxValue(i-1, j-size[i-1]) + values[i-1]} , j >= size(i-1)
     *
     * @param capacity 背包容量
     * @param values   每件物品的价值数组
     * @param sizes    每件物品的体积数组
     * @return 背包所能容纳物品的最大价值
     */
    public static int getBestPackageValueV1(int capacity, int[] values, int[] sizes) {
        int[][] resultTable = new int[values.length + 1][capacity + 1];
        int[][] path = new int[values.length + 1][capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (j < sizes[i - 1]) {
                    // 如果当前背包容量 < 当前物品体积
                    resultTable[i][j] = resultTable[i - 1][j];
                } else {
                    resultTable[i][j] = Math.max(resultTable[i - 1][j], resultTable[i - 1][j - sizes[i - 1]] + values[i - 1]);
                    // 标记商品放入背包
                    path[i][j] = 1;
                }
            }
        }
        System.out.println(Arrays.deepToString(resultTable));
        boolean[] item = new boolean[values.length];
        getBestPackageWhat(values.length, capacity, values, sizes, resultTable, item);
        System.out.println("最优组合解：" + Arrays.toString(item));

        getBestPackageWhatV2(path,sizes);

        return resultTable[values.length][capacity];
    }

    /**
     * 寻找最优组合解
     * 1) V(i,j)=V(i-1,j)时，说明没有选择第i个商品，则回到V(i-1,j)；
     * 2) V(i,j)=V(i-1,j-w(i-1))+v(i-1)时，说明装了第i个商品，该商品是最优解组成的一部分，随后我们得回到装该商品之前，即回到V(i-1,j-w(i-1))；
     * 3) 一直遍历到i＝0结束为止，所有解的组成都会找到。
     *
     * @param i           values.length
     * @param j           capacity
     * @param values      values
     * @param sizes       sizes
     * @param resultTable 结果表
     * @param item        商品标记数组
     */
    public static void getBestPackageWhat(int i, int j, int[] values, int[] sizes, int[][] resultTable, boolean[] item) {
        if (i > 0) {
            if (resultTable[i][j] == resultTable[i - 1][j]) {
                item[i - 1] = false;
                getBestPackageWhat(i - 1, j, values, sizes, resultTable, item);
            } else if ((j - sizes[i - 1] >= 0) && (resultTable[i][j] == (resultTable[i - 1][j - sizes[i - 1]] + values[i - 1]))) {
                item[i - 1] = true;
                getBestPackageWhat(i - 1, j - sizes[i - 1], values, sizes, resultTable, item);
            }
        }
    }

    public static void getBestPackageWhatV2(int[][] path, int[] sizes){
        int i = path.length-1;
        int j = path[0].length-1;
        while (i > 0 && j > 0) {
            if(path[i][j] == 1){
                System.out.printf("第%d个商品放入背包；\n",i);
                j -= sizes[i-1];
            }
            i--;
        }

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
        int[] results = new int[w + 1];

        // 填充一维数组
        for (int i = 1; i <= g.length; i++) {
            for (int j = w; j >= 1; j--) {
                if (j >= p[i - 1]) {
                    results[j] = Math.max(results[j], results[j - p[i - 1]] + g[i - 1]);
                }
            }
        }
        System.out.println(Arrays.toString(results));

        return results[w];
    }


    /**
     * 最大子序和
     * @param nums 数组
     * @return
     */
    public int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    public static void main(String[] args) {

        System.out.println("金矿问题Test");
        int w = 10;
        int[] p = {5, 5, 3, 4, 3};
        int[] g = {400, 500, 200, 300, 350};
//        System.out.println("最优收益：" + getBestGoldMiningV2(w, p, g));
        System.out.println("最优收益：" + getBestGoldMiningV3(w, p, g));

        System.out.println("背包问题Test");
        int capacity = 8;
        int[] values = {3, 4, 5, 6};
        int[] sizes = {2, 3, 4, 5};
        System.out.println("最优收益：" + getBestPackageValueV1(capacity, values, sizes));

    }

}
