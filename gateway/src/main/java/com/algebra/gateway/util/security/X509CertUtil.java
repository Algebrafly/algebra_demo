package com.algebra.gateway.util.security;

import com.algebra.gateway.util.Base64Util;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author al
 * @date 2022/2/17 16:48
 * @description
 */
@Slf4j
public class X509CertUtil {

    public static final String DEFAULT_KEY_TYPE = "PKCS12";
    public static final String DEFAULT_KEY_PAIR_GENERATOR = "RSA";
    public static final String DEFAULT_SIGNATURE = "SHA1withRSA";
    public static final String CERT_TYPE = "X509";
    public static final Integer DEFAULT_KEY_SIZE = 2048;

    static {
        // 系统添加BC加密算法
        Security.addProvider(new BouncyCastleProvider());
    }


    public static void createKeyStore(String issuer, Date startDate, Date endDate, String certDestPath,
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
                issueDn, serial, startDate, endDate, subjectDn, subjectPublicKeyInfo);
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

        File file = new File(certDestPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        FileOutputStream fout = new FileOutputStream(file);
        store.store(fout, keyPassword.toCharArray());
        fout.close();
    }

    public static X509Certificate readCertFile(String certFile) throws CertificateException, IOException {
        CertificateFactory factory = CertificateFactory.getInstance(CERT_TYPE);
        FileInputStream inputStream = new FileInputStream(certFile);
        X509Certificate certificate = (X509Certificate) factory.generateCertificate(inputStream);
        inputStream.close();
        return certificate;
    }

    public static void writeCertFileByKeyStore(String certFile, String keyStorePath, String storePwd)
            throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        char[] charArray = storePwd.toCharArray();
        KeyStore keyStore = KeyStore.getInstance(DEFAULT_KEY_TYPE);
        FileInputStream fis = new FileInputStream(keyStorePath);
        keyStore.load(fis, charArray);
        fis.close();
        Enumeration<String> enumas = keyStore.aliases();
        String keyAlias = enumas.nextElement();
        X509Certificate certificate = (X509Certificate) keyStore.getCertificate(keyAlias);
        File file = new File(certFile);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(certificate.getEncoded());
        outputStream.close();
    }


    public static void printCert(String certPath, String keyPassword) throws Exception {
        char[] charArray = keyPassword.toCharArray();
        KeyStore ks = KeyStore.getInstance(DEFAULT_KEY_TYPE);
        FileInputStream fis = new FileInputStream(certPath);
        ks.load(fis, charArray);
        fis.close();

        log.info("keystore type = {}", ks.getType());

        Enumeration<String> enumas = ks.aliases();
        String keyAlias = null;
        if (enumas.hasMoreElements()) {
            keyAlias = enumas.nextElement();
            log.info("alias=[{}]", keyAlias);
        }
        log.info("is key entry = {}", ks.isKeyEntry(keyAlias));

        PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, charArray);
        Certificate cert = ks.getCertificate(keyAlias);

        log.info("Base64-Cert Content :\n {}", new String(Base64.getEncoder().encode(cert.getEncoded())));
        PublicKey pubkey = cert.getPublicKey();

        log.info("cert class = {}", cert.getClass().getName());
        log.info("cert = {}", cert);
        log.info("public key: \n{}", new String(Base64.getEncoder().encode(pubkey.getEncoded())));
        log.info("private key: \n{}", new String(Base64.getEncoder().encode(prikey.getEncoded())));
    }

    public static PublicKey getPublicKey(String keyStorePath, String keyPassword) throws Exception {
        char[] charArray = keyPassword.toCharArray();
        KeyStore ks = KeyStore.getInstance(DEFAULT_KEY_TYPE);
        FileInputStream fis = new FileInputStream(keyStorePath);
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

    public static PrivateKey getPrivateKey(String keyStorePath, String keyPassword) throws Exception {
        char[] charArray = keyPassword.toCharArray();
        KeyStore ks = KeyStore.getInstance(DEFAULT_KEY_TYPE);
        FileInputStream fis = new FileInputStream(keyStorePath);
        ks.load(fis, charArray);
        fis.close();
        Enumeration<String> enumas = ks.aliases();
        String keyAlias = null;
        if (enumas.hasMoreElements()) {
            keyAlias = enumas.nextElement();
            return (PrivateKey) ks.getKey(keyAlias, charArray);
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
            throw new Exception("can not find alias: " + alias);
        }
    }

    public static void initialDefaultCa() {

        String storeFile = "/store/default.keystore";
        String certFile = "/store/default.cer";

        try {
            Properties properties = new Properties();
            InputStream resource = X509CertUtil.class.getResourceAsStream("/store/default.properties");
            properties.load(resource);

            String issuer = properties.getProperty("issue");
            BigInteger serial = BigInteger.valueOf(System.currentTimeMillis());
            String keyPassword = properties.getProperty("password");
            String alias = properties.getProperty("alias");

            createKeyStore(issuer, new Date(),
                    new SimpleDateFormat("yyyy-MM-dd").parse(properties.getProperty("end.date")),
                    storeFile, serial, keyPassword, alias);
            printCert(storeFile, keyPassword);

            writeCertFileByKeyStore(certFile, storeFile, keyPassword);
            X509Certificate certificate = readCertFile(certFile);
            PublicKey publicKey = certificate.getPublicKey();
            log.info(Base64Util.encoder(publicKey.getEncoded()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        initialDefaultCa();

    }

}
