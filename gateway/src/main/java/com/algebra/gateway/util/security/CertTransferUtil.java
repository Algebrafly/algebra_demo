package com.algebra.gateway.util.security;

import com.algebra.gateway.util.Base64Util;

import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

/**
 * @author al
 * @date 2022/2/18 16:43
 * @description 证书转换工具 [ps.暂未实现]
 * cer --> pem
 * pem --> cer
 */
public class CertTransferUtil {




    // 借用测试CaUtil
    public static void main(String[] args) throws NoSuchAlgorithmException, CertificateException, IOException {

        KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
        kpGen.initialize(2048);
        KeyPair kp = kpGen.generateKeyPair();
        BigInteger serial = BigInteger.valueOf(System.currentTimeMillis());
        CaUtil.issueCert("C=CN,ST=Shandong,L=QingDao,O=Test,OU=TestGroup,CN=www.test.com", new Date("2099/11/12"),
                "E:\\store\\test.cer", serial, kp.getPublic());


        System.out.println(Base64Util.encoder(kp.getPublic().getEncoded()));

        X509Certificate certificate = X509CertUtil.readCertFile("E:\\store\\test.cer");
        System.out.println(Base64Util.encoder(certificate.getPublicKey().getEncoded()));

        X509Certificate certificate2 = X509CertUtil.readCertFile("/store/default.cer");
        System.out.println(Base64Util.encoder(certificate2.getPublicKey().getEncoded()));

    }

}
