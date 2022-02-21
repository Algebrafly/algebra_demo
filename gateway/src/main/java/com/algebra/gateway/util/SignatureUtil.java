package com.algebra.gateway.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

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


    public byte[] sign(PrivateKey privateKey, byte[] message)
            throws GeneralSecurityException {
        Signature signature = Signature.getInstance(alg);
        signature.initSign(privateKey);
        signature.update(message);
        return signature.sign();
    }

    public boolean verify(PublicKey publicKey, byte[] message, byte[] sig) throws GeneralSecurityException {
        Signature signature = Signature.getInstance(alg);
        signature.initVerify(publicKey);
        signature.update(message);
        return signature.verify(sig);
    }


}
