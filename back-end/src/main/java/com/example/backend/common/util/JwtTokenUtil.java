package com.example.backend.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



import java.util.Date;

public class JwtTokenUtil {





    public static String getUserName(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().get("username", String.class);
    }

    //토큰의 유효 기간이 현재 시간 이전인지 여부를 확인
    public static boolean isExpired(String token, String secretKey) {
       return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
               .getBody().getExpiration().before(new Date());
    }

    public static String createToken(String username, String key, long expireTimeMs) {
        Claims claims = Jwts.claims(); //일종의 Map
        claims.put("username", username);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis())) //만든 날짜
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs)) //끝나는 날짜
                .signWith(SignatureAlgorithm.HS256, key) // key 를 가지고 HS256 으로 암호화
                .compact()
                ;
    }


    public static String createRefreshToken(String username, String key, long expireTimeMs) {
        Claims claims = Jwts.claims(); //일종의 Map
        claims.put("username", username);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
}
