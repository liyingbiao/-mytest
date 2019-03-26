package com.itheima.service.impl;

import com.itheima.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml","classpath:spring-security.xml"})
public class UserServiceImplTest {

    @Autowired
    private IUserService userService;

    @Test
    public void find(){
        System.out.println(userService.findById(1));
    }
}






















