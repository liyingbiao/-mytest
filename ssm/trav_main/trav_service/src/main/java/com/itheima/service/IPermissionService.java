package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Permission;

public interface IPermissionService {
    /**
     * 查询全部
     */
    PageInfo<Permission> findByPage(int pageNum, int pageSize);

    /**
     * 新增
     */
    void save(Permission permission);
}














