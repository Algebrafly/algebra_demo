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

        String b64encoded = Base64.getEncoder().encodeToString(input);
        String b64encoded2 = Base64.getEncoder().withoutPadding().encodeToString(input);
        System.out.println(b64encoded);
        System.out.println(b64encoded2);

        byte[] output = Base64.getDecoder().decode(b64encoded2);
        System.out.println(Arrays.toString(output));

        return b64encoded;
    }


    public static void main(String[] args) {
        byte[] input = new byte[] { (byte) 0xe4, (byte) 0xb8, (byte) 0xad, 0x21 };
        encoder(input);

    }

}
