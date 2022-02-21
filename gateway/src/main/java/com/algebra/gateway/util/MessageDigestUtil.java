package com.algebra.gateway.util;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author al
 * @date 2022/2/18 16:48
 * @description 哈希（消息摘要）算法工具类
 */
public class MessageDigestUtil {

    private static final byte[] H_KEY = new byte[] { 106, 70, -110, 125, 39, -20, 52, 56, 85, 9, -19, -72, 52, -53, 52, -45, -6, 119, -63,
            30, 20, -83, -28, 77, 98, 109, -32, -76, 121, -106, 0, -74, -107, -114, -45, 104, -104, -8, 2, 121, 6,
            97, -18, -13, -63, -30, -125, -103, -80, -46, 113, -14, 68, 32, -46, 101, -116, -104, -81, -108, 122,
            89, -106, -109 };

    public static String normalHash(String input, String alg) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(alg);
        md.update(input.getBytes(StandardCharsets.UTF_8));
        byte[] result = md.digest();
        return BytesUtils.bytesToHex(result);
    }

    public static String macHash(String input) throws NoSuchAlgorithmException, InvalidKeyException {
//        KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
//        SecretKey key = keyGen.generateKey();
        SecretKey key = new SecretKeySpec(H_KEY, "HmacMD5");
        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(key);
        mac.update(input.getBytes(StandardCharsets.UTF_8));
        byte[] result = mac.doFinal();
        return BytesUtils.bytesToHex(result);
    }


    public static void main(String[] args) throws Exception {

        System.out.println(macHash("bob"));

        // 创建一个MessageDigest实例:
        MessageDigest md = MessageDigest.getInstance("MD5");    // 输出是128 bits，即16 bytes
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1"); // 输出是160 bits，即20 bytes
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256"); // 输出是256 bits，即32 bytes
        // 反复调用update输入数据:
        md.update("Hello".getBytes(StandardCharsets.UTF_8));
        md.update("World".getBytes(StandardCharsets.UTF_8));
        byte[] result = md.digest();
        System.out.println(BytesUtils.bytesToHex(result));


        KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
        SecretKey key = keyGen.generateKey();
        // 打印随机生成的key:
        byte[] skey = key.getEncoded();
        System.out.println(BytesUtils.bytesToHex(skey));
        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(key);
        mac.update("HelloWorld".getBytes(StandardCharsets.UTF_8));
        byte[] result2 = mac.doFinal();
        System.out.println(BytesUtils.bytesToHex(result2));

    }

}
