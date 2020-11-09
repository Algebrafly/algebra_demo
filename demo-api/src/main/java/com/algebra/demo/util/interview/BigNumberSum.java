package com.algebra.demo.util.interview;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author al
 * @date 2020/11/9 10:58
 * @description 【小灰的算法之旅-java版实现】- 两个超大整数相加
 * 参考（大整数四则远算）：https://www.cnblogs.com/xxp17457741/p/7482495.html
 */
public class BigNumberSum {

    public static void main(String[] args) {

        System.out.println(bigNumberSum("426709752318", "95481253129"));

        // BigDecimal大数相加
        BigDecimal b = new BigDecimal("4267097523181235464");
        System.out.println(b.add(new BigDecimal("95481253129111111111109")));

    }

    public static String bigNumberSum(String bigNumOne, String bigNumTwo) {
        // 1. 把两个大整数用数组逆序存储，数组长度等于较大整数位+1
        int maxLength = Math.max(bigNumOne.length(), bigNumTwo.length());
        int[] arrayOne = new int[maxLength + 1];
        for (int i = 0; i < bigNumOne.length(); i++) {
            arrayOne[i] = bigNumOne.charAt(bigNumOne.length() - 1 - i) - '0';
        }
        System.out.println(Arrays.toString(arrayOne));

        int[] arrayTwo = new int[maxLength + 1];
        for (int i = 0; i < bigNumTwo.length(); i++) {
            arrayTwo[i] = bigNumTwo.charAt(bigNumTwo.length() - 1 - i) - '0';
        }
        System.out.println(Arrays.toString(arrayTwo));

        // 2. 构建result数组，数组长度等于较大整数位+1
        int[] result = new int[maxLength + 1];

        // 3. 遍历数组，按位相加
        for (int i = 0; i < result.length; i++) {
            int temp = result[i];
            temp += arrayOne[i];
            temp += arrayTwo[i];

            // 判断是否进位
            if (temp >= 10) {
                temp = temp - 10;
                result[i + 1] = 1;
            }
            result[i] = temp;
        }

        // 4. 把result数组再次逆序并转成String
        StringBuilder sb = new StringBuilder();
        //是否找到大整数的最高有效位
        boolean findFirst = false;
        for (int i = result.length - 1; i >= 0; i--) {
            if (!findFirst) {
                if (result[i] == 0) {
                    continue;
                }
                findFirst = true;
            }
            sb.append(result[i]);
        }

        return sb.toString();
    }


}
