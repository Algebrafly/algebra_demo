package com.algebra.gateway.util.security;

import com.algebra.gateway.util.Base64Util;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.nio.charset.StandardCharsets;
import java.security.*;

/**
 * @author al
 * @date 2022/2/21 17:43
 * @description 签名算法工具类
 * 用户总是使用自己的私钥进行签名，所以，私钥就相当于用户身份。而公钥用来给外部验证用户身份。
 * MD5withRSA
 * SHA1withRSA
 * SHA256withRSA
 */
public class SignatureUtil {

    private String alg;

    private PrivateKey privateKey;

    private PublicKey publicKey;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private SignatureUtil(String alg) {
        this.alg = alg;
    }

    private SignatureUtil(PrivateKey privateKey, PublicKey publicKey, String alg) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.alg = alg;
    }

    /**
     * 通过传入自己生成的密钥对来进行操作
     */
    public static SignatureUtil build(String alg) {
        return new SignatureUtil(alg);
    }

    /**
     * 使用自己生成的密钥对
     */
    public static SignatureUtil build(int size, String alg) throws NoSuchAlgorithmException {
        KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
        kpGen.initialize(size);
        KeyPair kp = kpGen.generateKeyPair();
        return new SignatureUtil(kp.getPrivate(), kp.getPublic(), alg);
    }

    public SignatureUtil setPrivateKey(PrivateKey key) {
        this.privateKey = key;
        return this;
    }

    public SignatureUtil setPublicKey(PublicKey key) {
        this.publicKey = key;
        return this;
    }

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public byte[] getPrivateKeyByte() {
        return this.privateKey.getEncoded();
    }

    public byte[] getPublicKeyByte() {
        return this.publicKey.getEncoded();
    }

    public String sign(String message) throws GeneralSecurityException {
        return Base64Util.encoder(sign(message.getBytes(StandardCharsets.UTF_8)));
    }

    public byte[] sign(byte[] message) throws GeneralSecurityException {
        Signature signature = Signature.getInstance(alg);
        signature.initSign(privateKey);
        signature.update(message);
        return signature.sign();
    }

    public boolean verify(String message, String sig) throws GeneralSecurityException {
        return verify(message.getBytes(StandardCharsets.UTF_8), Base64Util.decoder(sig));
    }

    public boolean verify(byte[] message, byte[] sig) throws GeneralSecurityException {
        Signature signature = Signature.getInstance(alg);
        signature.initVerify(publicKey);
        signature.update(message);
        return signature.verify(sig);
    }

    public static byte[] sign(PrivateKey privateKey, String alg, byte[] message) throws GeneralSecurityException {
        Signature signature = Signature.getInstance(alg);
        signature.initSign(privateKey);
        signature.update(message);
        return signature.sign();
    }

    public static boolean verify(PublicKey publicKey, String alg, byte[] message, byte[] sig) throws GeneralSecurityException {
        Signature signature = Signature.getInstance(alg);
        signature.initVerify(publicKey);
        signature.update(message);
        return signature.verify(sig);
    }


    public static void main(String[] args) throws GeneralSecurityException {

        String message = "Hello, I am Bob!";

        SignatureUtil signatureUtil = SignatureUtil.build(1024, "SHA1withRSA");
        String sign = signatureUtil.sign(message);
        System.out.println(sign);

        boolean verify = signatureUtil.verify(message, sign);
        System.out.println(verify);

    }

}
