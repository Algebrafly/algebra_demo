package com.algebra.demo.util.interview;

/**
 * @author al
 * @date 2020/11/9 8:58
 * @description 【小灰的算法之旅-java版实现】- 删除K个数字后的最小值
 * 依次求得局部最优解，最终得到全局最优解 —— 贪心算法
 */
public class RemoveKDigits {

    /**
     * 删除整数的k个数字，获得删除后的最小值
     * version 1.0
     *
     * @param num 原整数
     * @param k   删除数量
     * @return 新整数
     */
    public static String removeKDigitsV1(String num, int k) {
        String numNew = num;
        for (int i = 0; i < k; i++) {
            boolean hasCut = false;
            // 从左向右遍历，找到比自己右侧数字大的数字并删除
            for (int j = 0; j < numNew.length() - 1; j++) {
                if (numNew.charAt(j) > numNew.charAt(j + 1)) {
                    numNew = numNew.substring(0, j) + numNew.substring(j + 1);
                    hasCut = true;
                    break;
                }
            }
            // 如果没有找到要删除的数字，就删除最后一个数字
            if (!hasCut) {
                numNew = numNew.substring(0, numNew.length() - 1);
            }
            // 清除左上角的零
            numNew = removeZero(numNew);

        }
        // 如果左上角的数字都被清除了，直接返回0
        if (numNew.length() == 0) {
            numNew = "0";
        }

        return numNew;
    }

    /**
     * 删除整数的k个数字，获得删除后的最小值
     * version 2.0
     *
     * @param num 原整数
     * @param k   删除数量
     * @return 新整数
     */
    public static String removeKDigitsV2(String num, int k) {
        // 新整数的最终长度 = 原长度 - K
        int newLength = num.length() - k;
        // 创建一个栈，用于接收所有数字
        char[] stack = new char[num.length()];
        int top = 0;
        for (int i = 0; i < num.length(); i++) {
            // 遍历当前数字
            char c = num.charAt(i);

            // 当栈顶数字大于遍历到的当前数字时候，栈顶数字出栈（删除数字）
            while (top > 0 && stack[top - 1] > c && k > 0) {
                top -= 1;
                k -= 1;
            }
            stack[top++] = c;
        }
        // 找到栈中第一个非零数字的位置，以此构建新的整数字符串
        int offset = 0;
        while (offset < newLength && stack[offset] == '0') {
            offset++;
        }

        return offset == newLength ? "0" : new String(stack, offset, newLength - offset);
    }

    private static String removeZero(String num) {
        for (int i = 0; i < num.length() - 1; i++) {
            if (num.charAt(0) != '0') {
                break;
            }
            num = num.substring(1);
        }
        return num;
    }

    public static void main(String[] args) {

        System.out.println(removeKDigitsV1("1593212", 3));
        System.out.println(removeKDigitsV1("30200", 1));
        System.out.println(removeKDigitsV1("10", 2));
        System.out.println(removeKDigitsV1("541270936", 3));

        System.out.println("----------------------");

        System.out.println(removeKDigitsV2("1593212", 3));
        System.out.println(removeKDigitsV2("30200", 1));
        System.out.println(removeKDigitsV2("10", 2));
        System.out.println(removeKDigitsV2("30200", 1));
        System.out.println(removeKDigitsV2("10", 2));
        System.out.println(removeKDigitsV2("541270936", 3));

    }

}
