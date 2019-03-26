package com.itheima;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AppSpring {
    /**
     * 使用Spring提供的高级加密
     * ShaHash + 随机盐
     */
    public static void main(String[] args) {
        // 加密工具类
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 加密
        String str1 = passwordEncoder.encode("888");
        String str2 = passwordEncoder.encode("888");
        String str3 = passwordEncoder.encode("888");
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
        //$2a$10$hEOTR2AZ7Cq1hbGr1npO7.M/Q6vW9cN3bYXNrndhlBunp2bVDqUCS
        //$2a$10$sfq/CcR5NPg.am.JKNz3Ied5ZTwZIkuj8qfNR4EdUyJ5eC5fwXt2.
        //$2a$10$w7WLJAIOMXpuCzyHphCGV.NR3LtOlAiA97Hm4AsY1a7s072jOxkg6
        // 解密
        boolean flag1 = passwordEncoder.matches("888", str1);
        boolean flag2 = passwordEncoder.matches("888", str2);
        boolean flag3 = passwordEncoder.matches("888", str3);
        System.out.println(flag1);
        System.out.println(flag2);
        System.out.println(flag3);
    }
}
