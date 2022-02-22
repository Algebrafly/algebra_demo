package com.algebra.gateway.util.security;

import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Properties;

/**
 * @author al
 * @date 2022/1/25 11:30
 * @description 作为一个证书颁发机构CA，主要处理证书签发的工作
 */
public class CaUtil {

    public static final String CERT_TYPE = "X509";

    /**
     * 签发证书（使用系统内置默认 CA机构）
     *
     * @param issuer    证书签名信息
     * @param endDate   证书失效时间
     * @param certFile  证书生成路径
     * @param serial    串号
     * @param publicKey 自己的公钥
     * @return 成功/失败
     */
    public static boolean issueCert(String issuer, Date endDate, String certFile,
                             BigInteger serial, PublicKey publicKey) {
        try {
            Ca ca = Ca.getInstance();
            // 组装证书
            X500Name issueDn = new X500Name(issuer);
            X500Name subjectDn = new X500Name(issuer);
            // 组装公钥信息
            SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(
                    new ASN1InputStream(publicKey.getEncoded()).readObject());

            X509v3CertificateBuilder builder = new X509v3CertificateBuilder(
                    issueDn, serial, new Date(), endDate, subjectDn, subjectPublicKeyInfo);

            // 证书的签名数据
            ContentSigner sigGen = new JcaContentSignerBuilder(ca.getAlg()).build(ca.getPrivateKey());
            X509CertificateHolder holder = builder.build(sigGen);
            byte[] certBuf = holder.getEncoded();

            // 初始化X509证书
            X509Certificate certificate = (X509Certificate) CertificateFactory.getInstance(CERT_TYPE)
                    .generateCertificate(new ByteArrayInputStream(certBuf));

            File file = new File(certFile);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(certificate.getEncoded());
            outputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public PublicKey getDefaultPublicKey() {
        Ca ca = Ca.getInstance();
        return ca.getPublicKey();
    }


    /**
     * CA机构，拥有默认的证书
     * 可以使用默认内置证书进行其他证书签发
     */
    static class Ca {

        private static Ca ca;

        private String alg;

        private PrivateKey privateKey;

        private PublicKey publicKey;

        public static synchronized Ca getInstance() {
            if (ca == null) {
                ca = new Ca();
            }
            return ca;
        }

        public static synchronized Ca getInstance(String alg, PrivateKey privateKey, PublicKey publicKey) {
            if (ca == null) {
                ca = new Ca(alg, privateKey, publicKey);
            }
            return ca;
        }

        public static synchronized Ca getInstance(String alg) {
            if (ca == null) {
                ca = new Ca(alg);
            }
            return ca;
        }

        public static synchronized Ca getInstance(String alg, String keyStorePath, String password) {
            if (ca == null) {
                ca = new Ca(alg, keyStorePath, password);
            }
            return ca;
        }

        private Ca(String alg, PrivateKey privateKey, PublicKey publicKey) {
            this.alg = alg;
            this.privateKey = privateKey;
            this.publicKey = publicKey;
        }

        private Ca() {
            Properties properties = new Properties();
            InputStream resource = X509CertUtil.class.getResourceAsStream("/store/default.properties");
            try {
                properties.load(resource);
                this.alg = "SHA1withRSA";
                this.privateKey = X509CertUtil.getPrivateKey("/store/default.keystore",
                        String.valueOf(properties.get("password")));
                this.publicKey = X509CertUtil.getPublicKey("/store/default.keystore",
                        String.valueOf(properties.get("password")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private Ca(String alg) {
            Properties properties = new Properties();
            InputStream resource = X509CertUtil.class.getResourceAsStream("/store/default.properties");
            try {
                properties.load(resource);
                this.alg = alg;
                this.privateKey = X509CertUtil.getPrivateKey("/store/default.keystore",
                        String.valueOf(properties.get("password")));
                this.publicKey = X509CertUtil.getPublicKey("/store/default.keystore",
                        String.valueOf(properties.get("password")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private Ca(String alg, String keyStorePath, String password) {
            try {
                this.alg = alg;
                this.privateKey = X509CertUtil.getPrivateKey(keyStorePath, password);
                this.publicKey = X509CertUtil.getPublicKey(keyStorePath, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public PrivateKey getPrivateKey() {
            return privateKey;
        }

        public PublicKey getPublicKey() {
            return publicKey;
        }

        public String getAlg() {
            return alg;
        }
    }

}
