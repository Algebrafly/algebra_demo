package com.algebra.gateway.util;

import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;

/**
 * @author al
 * @date 2022/2/17 16:48
 * @description
 */
public class X509CertUtil {

    public static final String DEFAULT_KEY_TYPE ="PKCS12";
    public static final String DEFAULT_KEY_PAIR_GENERATOR ="RSA";
    public static final String DEFAULT_SIGNATURE ="SHA1withRSA";
    public static final String CERT_TYPE ="X509";
    public static final Integer DEFAULT_KEY_SIZE =2048;

    static {
        // 系统添加BC加密算法 以后系统中调用的算法都是BC的算法
        Security.addProvider(new BouncyCastleProvider());
    }


    public void createCert(String issuer, Date notBefore, Date notAfter, String certDestPath,
                           BigInteger serial, String keyPassword, String alias) throws Exception {

        //产生公私钥对
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(DEFAULT_KEY_PAIR_GENERATOR);
        kpg.initialize(DEFAULT_KEY_SIZE);
        KeyPair keyPair = kpg.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        // 组装证书
        X500Name issueDn = new X500Name(issuer);
        X500Name subjectDn = new X500Name(issuer);
        //组装公钥信息
        SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo
                .getInstance(new ASN1InputStream(publicKey.getEncoded())
                        .readObject());

        X509v3CertificateBuilder builder = new X509v3CertificateBuilder(
                issueDn, serial, notBefore, notAfter, subjectDn,
                subjectPublicKeyInfo);
        //证书的签名数据
        ContentSigner sigGen = new JcaContentSignerBuilder(DEFAULT_SIGNATURE).build(privateKey);
        X509CertificateHolder holder = builder.build(sigGen);
        byte[] certBuf = holder.getEncoded();
        X509Certificate certificate = (X509Certificate) CertificateFactory.getInstance(CERT_TYPE).generateCertificate(new ByteArrayInputStream(certBuf));
        // 创建KeyStore,存储证书
        KeyStore store = KeyStore.getInstance(DEFAULT_KEY_TYPE);
        store.load(null, null);
        store.setKeyEntry(alias, keyPair.getPrivate(), keyPassword.toCharArray(), new X509Certificate[] { certificate });
        FileOutputStream fout =new FileOutputStream(certDestPath);
        store.store(fout, keyPassword.toCharArray());
        fout.close();

    }



}
