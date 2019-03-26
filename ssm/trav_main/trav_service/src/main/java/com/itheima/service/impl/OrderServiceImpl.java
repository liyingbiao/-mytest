package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.IOrderDao;
import com.itheima.domain.Order;
import com.itheima.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

    // 注入dao
    @Autowired
    private IOrderDao orderDao;

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public PageInfo<Order> findByPage(int pageNum, int pageSize) {
        // 使用PageHelper分页，会自动对其后的第一行查询进行分页
        PageHelper.startPage(pageNum,pageSize);
        // 此查询会被分页
        List<Order> list = orderDao.findAll();
        return new PageInfo<Order>(list);
    }

}

















