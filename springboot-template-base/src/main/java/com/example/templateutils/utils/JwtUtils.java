package com.example.templateutils.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>Project: springboot-skills - JwtUtils
 * <p>Powered by echo On 2024-08-26 10:31:12
 *
 * @author sofency [sofency@qq.com]
 * @version 1.0
 * @since 17
 * token 验证工具类
 */
public class JwtUtils {
    private static final String SECRET = "token!Q@W#E$R";

    /**
     * 生产token
     */
    public static String getToken(Map<String, String> map, Integer days) {
        JWTCreator.Builder builder = JWT.create();
        // payload   将用户信息放到令牌里面
        map.forEach(builder::withClaim);
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE, days);

        builder.withExpiresAt(instance.getTime());// 指定令牌的过期时间
        return builder.sign(Algorithm.HMAC256(SECRET));
    }

    public static Map<String, String> parseToken(String token) {
        // 解析
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        // 获取指定值
        return verify.getClaims().entrySet().stream().collect(
                Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().asString()));
    }


    /**
     * 验证token
     */
    public static void verify(String token) {
        // 如果有任何验证异常，此处都会抛出异常
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        if (verify.getExpiresAt().before(new Date())) {
            throw new TokenExpiredException("token expire");
        }
    }
}
