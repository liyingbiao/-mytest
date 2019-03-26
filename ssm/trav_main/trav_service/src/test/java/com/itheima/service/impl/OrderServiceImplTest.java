package com.itheima.service.impl;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Order;
import com.itheima.service.IOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class OrderServiceImplTest {

    @Autowired
    private IOrderService orderService;

    @Test
    public void find(){
        PageInfo<Order> pageInfo = orderService.findByPage(1, 10);
        System.out.println(pageInfo);
    }
}






















