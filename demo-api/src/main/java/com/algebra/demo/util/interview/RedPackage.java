package com.algebra.demo.util.interview;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author al
 * @date 2020/11/13 15:16
 * @description 【小灰的算法之旅-java版实现】- 抢红包算法
 */
public class RedPackage {

    /**
     * 抢红包算法-二倍均值法
     * 假设剩余红包金额为m元，剩余人数为n，那么有如下公式。 每次抢到的金额 = 随机区间 [0.01，m /n × 2 - 0.01]
     * 局限性：即除最后一次外，其他每次抢到的金额都要小于剩余人均金额 的2倍，并不是完全自由地随机抢红包。
     *
     * @param totalAmt       总金额
     * @param totalPersonNum 总人数
     * @return 红包列表
     */
    public static List<Integer> divideRedPackage(Integer totalAmt, Integer totalPersonNum) {
        List<Integer> amtList = new ArrayList<>();
        Integer restAmt = totalAmt;
        Integer restPerson = totalPersonNum;

        Random random = new Random();

        for (int i = 0; i < totalPersonNum - 1; i++) {
            // 随机范围 [1,剩余人均金额的2倍-1]
            int amt = random.nextInt(restAmt / restPerson * 2 - 1) + 1;
            restAmt -= amt;
            restPerson--;
            amtList.add(amt);
        }
        amtList.add(restAmt);

        return amtList;
    }

    /**
     * 线段分割法
     *
     * @param money 总金额
     * @param n     人数
     * @return
     */
    public static List<Integer> divide(double money, int n) {
        //验证参数合理校验
        //为了使用random.nextInt(Integer)方法不得不先把红包金额放大100倍，最后在main函数里面再除以100
        //这样就可以保证每个人抢到的金额都可以精确到小数点后两位
        int fen = (int) (money * 100);
        if (fen < n || n < 1) {
            System.out.println("红包个数必须大于0，并且最小红包不少于1分");
        }
        List<Integer> boards = new ArrayList<>();
        boards.add(0);
        boards.add(fen);
        //红包个数和板砖个数的关系
        while (boards.size() <= n) {
            int index = new Random().nextInt(fen - 1) + 1;
            if (boards.contains(index)) {
                //保证板子的位置不相同
                continue;
            }
            boards.add(index);
        }

        //计算每个红包的金额，将两个板子之间的钱加起来
        Collections.sort(boards);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < boards.size() - 1; i++) {
            Integer e = boards.get(i + 1) - boards.get(i);
            list.add(e);
        }
        return list;

    }


    public static void main(String[] args) {

        List<Integer> amtList = divideRedPackage(1000, 10);
        for (Integer amt : amtList) {
            System.out.println("[二倍均值法]抢到金额：" + new BigDecimal(amt).divide(new BigDecimal(100)));
        }
        System.out.println("-------------------------------");
        List<Integer> accountList = divide(10, 10);
        for (Integer amt : accountList) {
            System.out.println("[线段分割法]抢到金额：" + new BigDecimal(amt).divide(new BigDecimal(100)));
        }

    }

}
