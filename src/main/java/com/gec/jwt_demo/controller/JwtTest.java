package com.gec.jwt_demo.controller;

import com.gec.jwt_demo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.HashMap;
import java.util.Map;

//https://www.jianshu.com/p/344a3d5bbab6
public class JwtTest {
    //简单的例子进行对jwt说明
    @Test
    public void JwtDemo(){
        //1.生产密钥
        String key="0123456789_0123456789_0123456789";//自定义一个字符串（字符串不能太短，不然报错）
        SecretKey secretKey=new SecretKeySpec(key.getBytes(), SignatureAlgorithm.HS256.getJcaName());

        //2.生成token
        String token= Jwts.builder()         // 创建jwt对象
                .setSubject("Json Web Token")//设置主题（声明信息）
                .signWith(secretKey)         //设置安全密钥（生产签名所需要的密钥和算法）
                .compact();                  //生成token（1.header和payload  2.生成签名 3.拼接字符串）
        System.out.println(token);

        //3.验证token
        try{
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            System.out.println("验证成功");
        }catch(Exception e)
        {
            System.out.println("验证失败");
        }

        //4.解析token
        Claims body=Jwts.parser()       //创建解析对象
                .setSigningKey(secretKey)//设置安全密钥（生成签名所需的密钥和算法）
                .parseClaimsJws(token)   //解析token
                .getBody();              //获取payload部分内容
        System.out.println("setSubject:"+body);
    }
    @Test
    public void JwtDome2(){
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("userId", 1001);
//        map.put("userName", "张三");
//        map.put("age",20);
//        String token = JwtUtils.createToken(map);
//        System.out.println(token);
        //eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4YWQwMThmNS05NWU1LTQ5NzEtYjZhMi1lNTQ1Y2EzYTM4YmYiLCJpYXQiOjE2MTAxNjY4ODMsImV4cCI6MTYxMDE3MDQ4MywidXNlck5hbWUiOiLlvKDkuIkiLCJ1c2VySWQiOjEwMDEsImFnZSI6MjB9.7fDzkHZ8NTfEwtSasXa0xyp-7zBFfkGFmeKA84OAni0

        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4YWQwMThmNS05NWU1LTQ5NzEtYjZhMi1lNTQ1Y2EzYTM4YmYiLCJpYXQiOjE2MTAxNjY4ODMsImV4cCI6MTYxMDE3MDQ4MywidXNlck5hbWUiOiLlvKDkuIkiLCJ1c2VySWQiOjEwMDEsImFnZSI6MjB9.7fDzkHZ8NTfEwtSasXa0xyp-7zBFfkGFmeKA84OAni0";
        String result = JwtUtils.verifyToken(token);
        System.out.println(result);
        Map<String,Object> map=JwtUtils.parseToken(token);
        System.out.println(map);
    }
}
