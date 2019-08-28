package com.ylsoft.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {

    public static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    // 使用BCryptPasswordEncoder的bean进行字符串的加密
    public static String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String str = "123";
        String encode = bCryptPasswordEncoder.encode(str);
        System.out.println(encode);
    }
}
