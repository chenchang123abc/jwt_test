package com.gec.jwt_demo.utils;



import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/*
    jwt工具类
 */
public class JwtUtils {
    //key：按照签名算法的字节长度设置key
    private final static String SECRET_KEY="0123456789_0123456789_0123456789";
    //过期时间
    private final static long TOKEN_EXPIRE_MILLIS=1000*60*60;
    //创建token
    public static String createToken(Map<String,Object> claimMap){
        long currentTimeMillis=System.currentTimeMillis();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis+TOKEN_EXPIRE_MILLIS))
                .addClaims(claimMap)
                .signWith(generateKey())
                .compact();
    }
    //验证token
    public static String verifyToken(String token){
        try{
            Jwts.parser().setSigningKey(generateKey()).parseClaimsJws(token);
            return "验证成功";
        }catch (Exception e)
        {
            e.printStackTrace();
            return "验证失败";
        }
    }
    //解析token
    public static Map<String,Object> parseToken(String token){
        return Jwts.parser()
                .setSigningKey(generateKey())
                .parseClaimsJws(token)
                .getBody();
    }
    //生成token
    public static Key generateKey(){
        return new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }
}
