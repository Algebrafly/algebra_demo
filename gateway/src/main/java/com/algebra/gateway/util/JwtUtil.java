package com.algebra.gateway.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
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


}
