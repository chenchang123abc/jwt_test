package com.gec.jwt_demo.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import static org.junit.jupiter.api.Assertions.*;

class JwtTestTest {

    @Test
    void jwtDemo() {
        //1.生产密钥
        String key="0123456789_0123456789_0123456789";//自定义一个字符串（注意这里定义的字符串不能太短不然报错）
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), SignatureAlgorithm.HS256.getJcaName());

        //2.生成token
        String token= Jwts.builder()         // 创建jwt对象
                .setSubject("Json Web Token")//设置主题（声明信息）
                .signWith(secretKey)         //设置安全密钥（生产签名所需要的密钥和算法）
                .compact();                  //生成token（1.header和payload  2.生成签名 3.拼接字符串）
        System.out.println(token);
    }
}