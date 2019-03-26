package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.IRoleDao;
import com.itheima.domain.Role;
import com.itheima.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService{
    @Autowired
    private IRoleDao roleDao;
    @Override
    public PageInfo<Role> findByPage(int pageNum, int pageSize) {
        // PageHelper分页
        PageHelper.startPage(pageNum,pageSize);
        // 调用dao查询全部，并封装分页参数返回
        return new PageInfo<Role>(roleDao.findAll());
    }

    @Override
    public void save(Role Role) {
        // 保存用户
        roleDao.save(Role);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}














