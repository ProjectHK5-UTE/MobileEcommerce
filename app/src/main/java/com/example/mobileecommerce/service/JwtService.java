package com.example.mobileecommerce.service;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.JWEDecryptionKeySelector;
import com.nimbusds.jose.proc.JWEKeySelector;
import com.nimbusds.jose.proc.SimpleSecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

import java.util.Date;

public class JwtService {
    public static final String USERNAME = "username";
    public static final String ROLE = "role";
    public static final String SECRET_KEY = "11111111111111111111111111111111";
    public static final int EXPIRE_TIME = 86400000;
    private static final String BEARER_PREFIX = "Bearer ";


    private static String stripBearerToken(String token) {
        if (token != null)
            token = token.startsWith(BEARER_PREFIX) ? token.substring(BEARER_PREFIX.length()) : token;
        return token;
    }

    //JWE
    private static JWTClaimsSet getClaimsFromToken(String token) {
        ConfigurableJWTProcessor<SimpleSecurityContext> jwtProcessor = new DefaultJWTProcessor<SimpleSecurityContext>();
        JWKSource<SimpleSecurityContext> jweKeySource = new ImmutableSecret<SimpleSecurityContext>(generateShareSecret());
        JWEKeySelector<SimpleSecurityContext> jweKeySelector =
                new JWEDecryptionKeySelector<SimpleSecurityContext>(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256, jweKeySource);
        jwtProcessor.setJWEKeySelector(jweKeySelector);
        token = stripBearerToken(token);
        JWTClaimsSet claims = null;
        try {
            claims = jwtProcessor.process(token, null);
            System.out.println(claims);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    // Gen thời hạn cho SecretKey
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + EXPIRE_TIME);
    }

    // Từ token lấy ra Date
    private static Date getExpirationDateFromToken(String token) {
        token = stripBearerToken(token);
        Date expiration = null;
        JWTClaimsSet claims = getClaimsFromToken(token);
        expiration = claims.getExpirationTime();
        return expiration;
    }

    // Từ token lấy ra Username
    public static String getUsernameFromToken(String token) {
        token = stripBearerToken(token);
        String username = null;
        try {
            JWTClaimsSet claims = getClaimsFromToken(token);
            username = claims.getStringClaim(USERNAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username;
    }

    // Từ token lấy ra Username
    public static String getRoleFromToken(String token) {
        token = stripBearerToken(token);
        String role = null;
        try {
            JWTClaimsSet claims = getClaimsFromToken(token);
            role = claims.getStringClaim(ROLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }
    // Gen khóa secret thành 32byte
    private static byte[] generateShareSecret() {
        // Generate 256-bit (32-byte) shared secret
        byte[] sharedSecret = new byte[32];
        sharedSecret = SECRET_KEY.getBytes();
        return sharedSecret;
    }

    // Kiểm tra token có hết hạn hay không
    private static Boolean isTokenExpired(String token) {
        token = stripBearerToken(token);
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // Kiểm tra token có hợp lệ không
    public static Boolean validateTokenLogin(String token) {
        token = stripBearerToken(token);
        if (token == null || token.trim().length() == 0) {
            return false;
        }
        String username = getUsernameFromToken(token);
        if (username == null || username.isEmpty()) {
            return false;
        }
        if (isTokenExpired(token)) {
            return false;
        }
        return true;
    }
}
