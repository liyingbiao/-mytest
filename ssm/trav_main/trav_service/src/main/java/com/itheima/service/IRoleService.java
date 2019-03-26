package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Role;

import java.util.List;

public interface IRoleService {
    /**
     * 查询全部
     */
    PageInfo<Role> findByPage(int pageNum,int pageSize);

    /**
     * 新增
     */
    void save(Role role);

    List<Role> findAll();
}














