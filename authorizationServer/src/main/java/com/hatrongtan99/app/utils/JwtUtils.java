package com.hatrongtan99.app.utils;

import com.hatrongtan99.app.security.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    @Autowired
    private ResourceLoader resource;

    static Long EXPIRED_TIME = (long) 60 * 1000 * 30;

    public String generateToken(Long id, Map<String, Object> claims) throws Exception {
        Date expiredDate = new Date(System.currentTimeMillis() + EXPIRED_TIME);
        PrivateKey privateKey = this.getPrivateKey(resource.getResource("classpath:key/jwtRSA256.pem").getURI());
        return Jwts.builder()
                .subject(String.valueOf(id))
                .issuedAt(new Date())
                .expiration(expiredDate)
                .claims(claims)
                .signWith(privateKey, Jwts.SIG.RS256)
                .compact();
    }

    public String getToken(UserPrincipal principal) throws Exception {
        Set<String> roles =principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", principal.getUsername());
        claims.put("roles", roles);
        return generateToken(principal.getId(), claims);
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
        PublicKey publicKey = this.getPublicKey(resource.getResource("classpath:key/jwtRSA256.pub.crt").getURI());
        return Jwts.parser()
                .verifyWith(publicKey)
                .build()
                .parseSignedClaims(token).getPayload();
    }

    private PrivateKey getPrivateKey(URI file) throws Exception {
        String key = Files.readString(Path.of(file), Charset.defaultCharset());

        String privateKeyPEM = key
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replaceAll(System.lineSeparator(), "")
                .replace("-----END PRIVATE KEY-----", "");

        byte[] encoded = Base64.decodeBase64(privateKeyPEM);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        return keyFactory.generatePrivate(keySpec);
    }

    private PublicKey getPublicKey(URI file) throws Exception {
        String key = Files.readString(Path.of(file), Charset.defaultCharset());
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
