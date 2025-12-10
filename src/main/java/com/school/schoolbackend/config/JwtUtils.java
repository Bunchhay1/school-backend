package com.school.schoolbackend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private static final String SECRET = "MySuperSecretKeyForSchoolSystemMustBeVeryLong123456";
    private static final Key KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    // 1. បង្កើត Token (Generate)
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 ថ្ងៃ
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // 2. អាន Username ពី Token (Extract Username)
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    // 3. ឆែកមើលថា Token ត្រឹមត្រូវឬអត់? (Validate)
    public boolean validateToken(String token) {
        try {
            getClaims(token); // បើ Token ក្លែងក្លាយ វានឹង Error នៅត្រង់នេះ
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}