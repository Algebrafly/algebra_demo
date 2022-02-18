package com.algebra.gateway.util;

import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Date;
import java.util.Enumeration;

/**
 * @author al
 * @date 2022/2/17 16:48
 * @description
 */
public class X509CertUtil {

    public static final String DEFAULT_KEY_TYPE = "PKCS12";
    public static final String DEFAULT_KEY_PAIR_GENERATOR = "RSA";
    public static final String DEFAULT_SIGNATURE = "SHA1withRSA";
    public static final String CERT_TYPE = "X509";
    public static final Integer DEFAULT_KEY_SIZE = 2048;

    static {
        // 系统添加BC加密算法 以后系统中调用的算法都是BC的算法
        Security.addProvider(new BouncyCastleProvider());
    }


    public static void createCert(String issuer, Date notBefore, Date notAfter, String certDestPath,
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
        // 组装公钥信息
        SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(
                new ASN1InputStream(publicKey.getEncoded()).readObject());

        X509v3CertificateBuilder builder = new X509v3CertificateBuilder(
                issueDn, serial, notBefore, notAfter, subjectDn, subjectPublicKeyInfo);
        // 证书的签名数据
        ContentSigner sigGen = new JcaContentSignerBuilder(DEFAULT_SIGNATURE).build(privateKey);
        X509CertificateHolder holder = builder.build(sigGen);
        byte[] certBuf = holder.getEncoded();

        // 初始化X509证书
        X509Certificate certificate = (X509Certificate) CertificateFactory.getInstance(CERT_TYPE)
                .generateCertificate(new ByteArrayInputStream(certBuf));

        // 创建KeyStore,存储证书
        KeyStore store = KeyStore.getInstance(DEFAULT_KEY_TYPE);
        store.load(null, null);
        store.setKeyEntry(alias, keyPair.getPrivate(), keyPassword.toCharArray(), new X509Certificate[]{certificate});

        FileOutputStream fout = new FileOutputStream(certDestPath);
        store.store(fout, keyPassword.toCharArray());
        fout.close();
    }

    public static void printCert(String certPath, String keyPassword) throws Exception {
        char[] charArray = keyPassword.toCharArray();
        KeyStore ks = KeyStore.getInstance(DEFAULT_KEY_TYPE);
        FileInputStream fis = new FileInputStream(certPath);
        ks.load(fis, charArray);
        fis.close();

        System.out.println("keystore type=" + ks.getType());

        Enumeration<String> enumas = ks.aliases();
        String keyAlias = null;
        if (enumas.hasMoreElements()) {
            keyAlias = enumas.nextElement();
            System.out.println("alias=[" + keyAlias + "]");
        }
        System.out.println("is key entry=" + ks.isKeyEntry(keyAlias));

        PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, charArray);
        Certificate cert = ks.getCertificate(keyAlias);

        System.out.println("Base64-Cert Content :\n" + new String(Base64.getEncoder().encode(cert.getEncoded())));
        PublicKey pubkey = cert.getPublicKey();

        System.out.println("cert class = " + cert.getClass().getName());
        System.out.println("cert = " + cert);
        System.out.println("public key: \n " + new String(Base64.getEncoder().encode(pubkey.getEncoded())));
        System.out.println("private key: \n " + new String(Base64.getEncoder().encode(prikey.getEncoded())));
    }

    public PublicKey getPublicKey(String certPath, String keyPassword) throws Exception {
        char[] charArray = keyPassword.toCharArray();
        KeyStore ks = KeyStore.getInstance(DEFAULT_KEY_TYPE);
        FileInputStream fis = new FileInputStream(certPath);
        ks.load(fis, charArray);
        fis.close();
        Enumeration<String> enumas = ks.aliases();
        String keyAlias = null;
        if (enumas.hasMoreElements()) {
            keyAlias = enumas.nextElement();
            Certificate certificate = ks.getCertificate(keyAlias);
            return certificate.getPublicKey();
        }
        return null;
    }

    public PrivateKey getPrivateKey(String certPath, String keyPassword) throws Exception {
        char[] charArray = keyPassword.toCharArray();
        KeyStore ks = KeyStore.getInstance(DEFAULT_KEY_TYPE);
        FileInputStream fis = new FileInputStream(certPath);
        ks.load(fis, charArray);
        fis.close();
        Enumeration<String> enumas = ks.aliases();
        String keyAlias = null;
        if (enumas.hasMoreElements()) {
            keyAlias = enumas.nextElement();
            return (PrivateKey)ks.getKey(keyAlias, charArray);
        }
        return null;
    }

    public void certDelayTo(Date endTime, String certPath, String password) throws Exception {

    }

    public void changePassword(String certPath, String oldPwd, String newPwd) throws Exception {
        KeyStore ks = KeyStore.getInstance(DEFAULT_KEY_TYPE);
        FileInputStream fis = new FileInputStream(certPath);
        ks.load(fis, oldPwd.toCharArray());
        fis.close();
        FileOutputStream output = new FileOutputStream(certPath);
        ks.store(output, newPwd.toCharArray());
        output.close();
    }

    public void deleteAlias(String certPath, String password, String alias, String entry) throws Exception {
        char[] charArray = password.toCharArray();
        KeyStore ks = KeyStore.getInstance(DEFAULT_KEY_TYPE);
        FileInputStream fis = new FileInputStream(certPath);
        ks.load(fis, charArray);
        fis.close();
        if (ks.containsAlias(alias)) {
            ks.deleteEntry(entry);
            FileOutputStream output = new FileOutputStream(certPath);
            ks.store(output, password.toCharArray());
            output.close();
        } else {
            throw new Exception("该证书未包含别名--->" + alias);
        }
    }

    public static KeyStore loadKeyStore(String keyStoreFile, String password) {
        try (InputStream input = X509CertUtil.class.getResourceAsStream(keyStoreFile)) {
            if (input == null) {
                throw new RuntimeException("file not found in classpath: " + keyStoreFile);
            }
            KeyStore ks = KeyStore.getInstance(DEFAULT_KEY_TYPE);
            ks.load(input, password.toCharArray());
            return ks;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] encrypt(X509Certificate certificate, byte[] message) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(certificate.getPublicKey().getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, certificate.getPublicKey());
        return cipher.doFinal(message);
    }

    public static byte[] decrypt(PrivateKey privateKey, byte[] data) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    public static byte[] sign(PrivateKey privateKey, X509Certificate certificate, byte[] message)
            throws GeneralSecurityException {
        Signature signature = Signature.getInstance(certificate.getSigAlgName());
        signature.initSign(privateKey);
        signature.update(message);
        return signature.sign();
    }

    public static boolean verify(X509Certificate certificate, byte[] message, byte[] sig) throws GeneralSecurityException {
        Signature signature = Signature.getInstance(certificate.getSigAlgName());
        signature.initVerify(certificate);
        signature.update(message);
        return signature.verify(sig);
    }

    public static void main(String[] args) {

        String issuer = "C=CN,ST=BJ,L=BJ,O=test,OU=testGroup,CN=localhost";
        String certDestPath = "E:\\MyProject\\algebra_demo\\gateway\\src\\main\\resources\\store\\code-test.keystore";
        BigInteger serial = BigInteger.valueOf(System.currentTimeMillis());
        String keyPassword = "123456";
        String alias = "test-code";

        try {
//            createCert(issuer, new Date(), new Date("2017/09/27"), certDestPath, serial, keyPassword, alias);
            printCert(certDestPath, keyPassword);

            System.out.println("*************************************");
            byte[] message = "Hello, use X.509 cert!".getBytes("UTF-8");
            // 读取KeyStore:
            KeyStore ks = loadKeyStore("/store/my.keystore", "123456");
            // 读取私钥:
            PrivateKey privateKey = (PrivateKey) ks.getKey("mykeystore", "123456".toCharArray());
            // 读取证书:
            X509Certificate certificate = (X509Certificate) ks.getCertificate("mykeystore");
            // 加密:
            byte[] encrypted = encrypt(certificate, message);
            System.out.println(String.format("encrypted: %x", new BigInteger(1, encrypted)));
            // 解密:
            byte[] decrypted = decrypt(privateKey, encrypted);
            System.out.println("decrypted: " + new String(decrypted, StandardCharsets.UTF_8));
            // 签名:
            byte[] sign = sign(privateKey, certificate, message);
            System.out.println(String.format("signature: %x", new BigInteger(1, sign)));
            // 验证签名:
            boolean verified = verify(certificate, message, sign);
            System.out.println("verify: " + verified);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
