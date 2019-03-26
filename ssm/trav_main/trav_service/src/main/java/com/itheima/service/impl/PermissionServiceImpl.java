package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.IPermissionDao;
import com.itheima.domain.Permission;
import com.itheima.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService{
    @Autowired
    private IPermissionDao permissionDao;
    @Override
    public PageInfo<Permission> findByPage(int pageNum, int pageSize) {
        // PageHelper分页
        PageHelper.startPage(pageNum,pageSize);
        // 调用dao查询全部，并封装分页参数返回
        return new PageInfo<Permission>(permissionDao.findAll());
    }

    @Override
    public void save(Permission permission) {
        // 保存用户
        permissionDao.save(permission);
    }
}














