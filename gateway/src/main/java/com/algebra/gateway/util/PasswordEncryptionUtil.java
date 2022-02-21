package com.algebra.gateway.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;

/**
 * @author al
 * @date 2022/2/21 10:35
 * @description 口令加密算法工具类 （口令任意，是普通对称加密的升级版：需要自己生成 16 bytes随机Salt）
 */
public class PasswordEncryptionUtil {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static String encrypt(String password, byte[] salt, String input) throws GeneralSecurityException {
        return Base64Util.encoder(encrypt(password, salt, input.getBytes(StandardCharsets.UTF_8)));
    }

    public static byte[] encrypt(String password, byte[] salt, byte[] input) throws GeneralSecurityException {
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        SecretKeyFactory skeyFactory = SecretKeyFactory.getInstance("PBEwithSHA1and128bitAES-CBC-BC");
        SecretKey skey = skeyFactory.generateSecret(keySpec);
        PBEParameterSpec pbeps = new PBEParameterSpec(salt, 1000);
        Cipher cipher = Cipher.getInstance("PBEwithSHA1and128bitAES-CBC-BC");
        cipher.init(Cipher.ENCRYPT_MODE, skey, pbeps);
        return cipher.doFinal(input);
    }

    public static String decrypt(String password, byte[] salt, String input) throws GeneralSecurityException {
        return new String(decrypt(password, salt, Base64Util.decoder(input)), StandardCharsets.UTF_8);
    }

    public static byte[] decrypt(String password, byte[] salt, byte[] input) throws GeneralSecurityException {
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        SecretKeyFactory skeyFactory = SecretKeyFactory.getInstance("PBEwithSHA1and128bitAES-CBC-BC");
        SecretKey skey = skeyFactory.generateSecret(keySpec);
        PBEParameterSpec pbeps = new PBEParameterSpec(salt, 1000);
        Cipher cipher = Cipher.getInstance("PBEwithSHA1and128bitAES-CBC-BC");
        cipher.init(Cipher.DECRYPT_MODE, skey, pbeps);
        return cipher.doFinal(input);
    }

    public static byte[] getSaltDefault() throws NoSuchAlgorithmException {
        return SecureRandom.getInstanceStrong().generateSeed(16);
    }

    public static void main(String[] args) throws Exception {
        // 原文:
        String message = "Hello, world!";
        // 加密口令:
        String password = "hello12345";
        // 16 bytes随机Salt:
        byte[] salt = getSaltDefault();
//        System.out.println(Base64Util.encoder(salt));
        System.out.printf("salt: %032x\n", new BigInteger(1, salt));
        // 加密:
        String encrypted = encrypt(password, salt, message);
        System.out.println("encrypted: " + encrypted);
        // 解密:
        String decrypted = decrypt(password, salt, encrypted);
        System.out.println("decrypted: " + decrypted);
    }

}
