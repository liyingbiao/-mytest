package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Order;
import com.itheima.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("order")
@PreAuthorize("hasAnyRole('ROLE_USER')")
public class OrderController {

    // 注入service
    @Autowired
    private IOrderService orderService;

    /**
     * 1. 产品列表
     */
    @RequestMapping("findAll")
    public ModelAndView findAll(){
        //1.1 查询产品
        List<Order> list = orderService.findAll();
        //1.2 返回结果
        ModelAndView mv = new ModelAndView();
        mv.addObject("list",list);
        mv.setViewName("order-list");
        return mv;
    }

    @RequestMapping("findByPage")
    public ModelAndView findByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "2") int pageSize){
        //1.1 查询产品
        PageInfo<Order> pageInfo = orderService.findByPage(pageNum,pageSize);
        //1.2 返回结果
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("order-list");
        return mv;
    }

}
















