package com.algebra.gateway.util;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;

/**
 * @author al
 * @date 2022/2/21 11:15
 * @description 非对称加密算法工具类（加密AES口令）
 */
public class AsymmetricEncryptionUtil {

    private PrivateKey privateKey;

    private PublicKey publicKey;

    private AsymmetricEncryptionUtil() {
    }

    private AsymmetricEncryptionUtil(PrivateKey privateKey, PublicKey publicKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    /**
     * 通过传入自己生成的密钥对来进行操作
     */
    public static AsymmetricEncryptionUtil build() {
        return new AsymmetricEncryptionUtil();
    }

    /**
     * 使用自己生成的密钥对
     */
    public static AsymmetricEncryptionUtil build(int size) throws NoSuchAlgorithmException {
        KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
        kpGen.initialize(size);
        KeyPair kp = kpGen.generateKeyPair();
        return new AsymmetricEncryptionUtil(kp.getPrivate(), kp.getPublic());
    }

    public AsymmetricEncryptionUtil setPrivateKey(PrivateKey key) {
        this.privateKey = key;
        return this;
    }

    public AsymmetricEncryptionUtil setPublicKey(PublicKey key) {
        this.publicKey = key;
        return this;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public byte[] getPrivateKeyByte() {
        return this.privateKey.getEncoded();
    }

    public byte[] getPublicKeyByte() {
        return this.publicKey.getEncoded();
    }

    public String encrypt(String message) throws GeneralSecurityException {
        return Base64Util.encoder(encrypt(message.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 用公钥加密
     */
    public byte[] encrypt(byte[] message) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);
        return cipher.doFinal(message);
    }


    public String decrypt(String input) throws GeneralSecurityException {
        return new String(decrypt(Base64Util.decoder(input)), StandardCharsets.UTF_8);
    }

    /**
     * 用私钥解密
     */
    public byte[] decrypt(byte[] input) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, this.privateKey);
        return cipher.doFinal(input);
    }


    public static void main(String[] args) throws GeneralSecurityException {

        AsymmetricEncryptionUtil rsa = AsymmetricEncryptionUtil.build(1024);
        String encrypt = rsa.encrypt("Hello World");
        System.out.println(encrypt);

        PrivateKey privateKey = rsa.getPrivateKey();
        String decrypt = AsymmetricEncryptionUtil.build().setPrivateKey(privateKey).decrypt(encrypt);
        System.out.println(decrypt);

    }

}
