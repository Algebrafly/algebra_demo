package com.algebra.gateway.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * @author al
 * @date 2022/2/16 11:16
 * @description
 */
public class JwtUtil {

    public static final String KEY = "algebra";

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decodeBase64(KEY);
        SecretKeySpec key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    public String createToken(Map<String,Object> param) {
        String token = JWT.create().withAudience("userId")
                .withClaim("admin", "admin").sign(Algorithm.HMAC256("pwd"));

        return "";
    }

    public String resolveToken(String token) {
        List<String> audience = JWT.decode(token).getAudience();

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("pwd")).build();
        DecodedJWT verify = verifier.verify(token);

        return "";
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        Security.addProvider(new BouncyCastleProvider());
        // 按名称正常调用:
        MessageDigest md = MessageDigest.getInstance("RipeMD160");

        String str = "hello world";
//        md.update();
        byte[] digest1 = md.digest(str.getBytes(StandardCharsets.UTF_8));
        System.err.println(Arrays.toString(digest1));
        System.out.println(new BigInteger(1, digest1).toString(16));

        md.update(str.getBytes());
        byte[] digest2 = md.digest();
        System.err.println(Arrays.toString(digest2));

        boolean equal = MessageDigest.isEqual(digest1, digest2);
        System.err.println(equal);

        int digestLength = md.getDigestLength();
        System.err.println(digestLength);

        String algorithm = md.getAlgorithm();
        System.err.println(algorithm);

        Provider provider = md.getProvider();
        System.err.println(provider);


    }


}
