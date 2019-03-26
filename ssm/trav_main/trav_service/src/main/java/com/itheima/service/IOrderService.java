package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Order;

import java.util.List;

public interface IOrderService {
    /**
     * 查询全部订单
     */
    List<Order> findAll();

    /**
     * 分页查询
     * @param pageNum 当前页
     * @param pageSize 页大小
     * @return 通过PageHelper提高的PageInfo封装分页参数并返回
     */
    PageInfo<Order> findByPage(int pageNum, int pageSize);
}




















