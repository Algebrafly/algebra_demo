package com.algebra.gateway.util;

import java.util.Arrays;
import java.util.Base64;

/**
 * @author al
 * @date 2022/2/18 16:48
 * @description Base64 & 二进制工具类 [没有过度封装，就是个测试工具类]
 */
public class Base64Util {

    public static String encoder(byte[] input) {
        return Base64.getEncoder().encodeToString(input);
    }

    public static byte[] decoder(String input) {
        return Base64.getDecoder().decode(input);
    }


    public static void main(String[] args) {
//        byte[] input = new byte[] { (byte) 0xe4, (byte) 0xb7, (byte) 0xad, 0x20 };
        // byte是一个字节,范围是(-128到128) 存储大小是8位有符号数
        byte[] input = new byte[]{-28, -71, -83, 32};
        System.out.println(encoder(input));

        // 228(10) = 0xe4 = 11100100(2)
        byte[] as = BytesUtils.getBytes(228);
        System.out.println(Arrays.toString(as));

        byte[] as1 = BytesUtils.getBytes(127);
        System.out.println(Arrays.toString(as1));  // 127

        byte[] as2 = BytesUtils.getBytes(128);
        System.out.println(Arrays.toString(as2));  // -128

        byte[] as3 = BytesUtils.getBytes(129);
        System.out.println(Arrays.toString(as3));  // -127

        byte[] as4 = BytesUtils.getBytes(130);
        System.out.println(Arrays.toString(as4));  // -126

        byte[] as5 = BytesUtils.getBytes(255);
        System.out.println(Arrays.toString(as5));  // -1

        byte[] as6 = BytesUtils.getBytes(256);
        System.out.println(Arrays.toString(as6));  // 0

        // 01111111.11111111.11111111.11111111
        // 127.255.255.255
        // 127.-1.-1.-1
        byte[] as7 = BytesUtils.getBytes(0x7fffffff);  // 2147483647
        System.out.println(Arrays.toString(as7));

        // 10000000.00000000.00000000.00000000
        // 128.0.0.0
        // -128.0.0.0
        byte[] as8 = BytesUtils.getBytes(-2147483648L);
        System.out.println(Arrays.toString(as8));

        byte[] as9 = BytesUtils.getBytes(1);
        System.out.println(Arrays.toString(as9));

        // 【VIP】正数缺失位补0，负数缺失位补1

        // 11111111.11111111.11111111.11111111
        // 255.255.255.255
        // -1.-1.-1.-1
        System.out.println(Integer.toHexString(-1));
        byte[] as10 = BytesUtils.getBytes(-1);
        System.out.println(Arrays.toString(as10));


        // 11111111.11111111.11111111.11111110
        // 255.255.255.254
        // -1.-1.-1.-2
        System.out.println(Integer.toHexString(-2));
        byte[] as11 = BytesUtils.getBytes(-2);
        System.out.println(Arrays.toString(as11));

        System.out.println(Integer.toHexString(-128));
        byte[] as12 = BytesUtils.getBytes(-128);
        System.out.println(Arrays.toString(as12));

        // 一个从-128 到 127 的数轴，每255位数作为一个循环单元，也就是byte数组的一个元素（八个二进制为一位：1byte = 8bit，正好2^8 = 256）

    }

}
