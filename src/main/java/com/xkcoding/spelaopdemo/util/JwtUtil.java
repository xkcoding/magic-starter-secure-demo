package com.xkcoding.spelaopdemo.util;

import com.xkcoding.spelaopdemo.config.properties.SecureProperties;
import com.xkcoding.spelaopdemo.model.SecureUser;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * JWT工具类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/9/18 18:04
 */
@Slf4j
@RequiredArgsConstructor
public class JwtUtil {
    public static final String USER_ID = "id";
    public static final String USERNAME = "username";
    public static final String PERMISSIONS = "permissions";
    private final SecureProperties secureProperties;

    /**
     * 从token中获取claim
     *
     * @param token token
     * @return claim
     */
    public Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser().setSigningKey(this.secureProperties.getJwt().getSecret().getBytes()).parseClaimsJws(token).getBody();

        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            throw new SecurityException("Token 不合法", e);
        }
    }

    /**
     * 获取token的过期时间
     *
     * @param token token
     * @return 过期时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    /**
     * 判断token是否过期
     *
     * @param token token
     * @return 已过期返回true，未过期返回false
     */
    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 为指定用户生成token
     *
     * @param secureUser 用户信息
     * @return token
     */
    public String generateToken(SecureUser secureUser) {
        Map<String, Object> claims = new HashMap<>(3);
        claims.put(USER_ID, secureUser.getId());
        claims.put(USERNAME, secureUser.getUsername());
        claims.put(PERMISSIONS, secureUser.getPermissions());
        Date createdTime = new Date();
        Date expirationTime = getExpirationTime();

        byte[] keyBytes = secureProperties.getJwt().getSecret().getBytes();
        SignatureAlgorithm algorithm = secureProperties.getJwt().getAlgorithm();

        return Jwts.builder().setClaims(claims).setIssuedAt(createdTime).setExpiration(expirationTime).signWith(algorithm, keyBytes).compact();
    }

    /**
     * 计算token的过期时间
     *
     * @return 过期时间
     */
    private Date getExpirationTime() {
        return new Date(System.currentTimeMillis() + secureProperties.getJwt().getTimeout().toMillis());
    }

    /**
     * 判断token是否非法
     *
     * @param token token
     * @return 未过期返回true，否则返回false
     */
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
}