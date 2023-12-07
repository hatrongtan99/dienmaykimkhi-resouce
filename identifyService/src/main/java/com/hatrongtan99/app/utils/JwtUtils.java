package com.hatrongtan99.app.utils;

import com.nimbusds.jose.Algorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
@Component
public class JwtUtils {

    static Long EXPIRED_TIME = (long) 60 * 1000 * 30;

    public String generateToken(Long id, Map<String, Object> claims) throws Exception {
        Date expiredDate = new Date(System.currentTimeMillis() + EXPIRED_TIME);
        PrivateKey privateKey = this.getPrivateKey(new File(ClassLoader.getSystemResource("key/jwtRSA256.pem").getFile()));
        return Jwts.builder()
                .subject(String.valueOf(id))
                .issuedAt(new Date())
                .expiration(expiredDate)
                .claims(claims)
                .signWith(privateKey, Jwts.SIG.RS256)
                .compact();
    }

    public Long getUserId(String token) throws Exception {
        return Long.valueOf(extractClaims(token, Claims::getSubject));
    }
    public <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) throws Exception {
        return claimsTFunction.apply(this.extractAllClaims(token));
    }

    public boolean verifyToken(String token, String fullName) throws Exception {
        String usernameFromToken = (String) this.extractClaims(token, (claims -> claims.get("username")));
        if (usernameFromToken == null) {
            return false;
        }

        return (usernameFromToken.equals(fullName) && new Date().before(extractClaims(token, Claims::getExpiration)));
    }
    private Claims extractAllClaims(String token) throws Exception {
        PublicKey publicKey = this.getPublicKey(new File(ClassLoader.getSystemResource("key/jwtRSA256.pub.crt").getFile()));
        return Jwts.parser()
                .verifyWith(publicKey)
                .build()
                .parseSignedClaims(token).getPayload();
    }

    private PrivateKey getPrivateKey(File file) throws Exception {
        String key = Files.readString(file.toPath(), Charset.defaultCharset());

        String privateKeyPEM = key
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replaceAll(System.lineSeparator(), "")
                .replace("-----END PRIVATE KEY-----", "");

        byte[] encoded = Base64.decodeBase64(privateKeyPEM);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        return keyFactory.generatePrivate(keySpec);
    }

    private PublicKey getPublicKey(File file) throws Exception {
        String key = Files.readString(file.toPath(), Charset.defaultCharset());

        String publicKeyPEM = key
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replaceAll(System.lineSeparator(), "")
                .replace("-----END PUBLIC KEY-----", "");

        byte[] encoded = Base64.decodeBase64(publicKeyPEM);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
        return  keyFactory.generatePublic(keySpec);
    }

}
