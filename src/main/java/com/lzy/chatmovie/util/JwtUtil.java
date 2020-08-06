package com.lzy.chatmovie.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Jwt 工具类
 * @author lzy
 * @since 2020.2.2
 */

@Component
public class JwtUtil {

    private final static String KEY = "chatmovie";

    /**
     * 生成 JWT
     * @param id 用户ID
     * @param subject 加密内容
     *                signWith 加盐，KEY可自定
     * @return
     */
    public String createJWT(String id, String subject)
    {
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256,KEY);
        return builder.compact();
    }

    /**
     * 解析 JWT
     * @param jwtStr
     * @return
     */
    public Claims parseJWT(String jwtStr)
    {
        return Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(jwtStr)
                .getBody();
    }

}
