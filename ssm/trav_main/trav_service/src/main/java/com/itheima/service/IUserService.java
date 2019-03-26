package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * service接口必须要继承UserDetailsService
 */
public interface IUserService extends UserDetailsService {
    /**
     * 分页查询
     */
    PageInfo<SysUser> findByPage(int pageNum,int pageSize);

    /**
     * 保存用户
     * @param user
     */
    void save(SysUser user);

    /**
     * 主键查询
     * @param id
     * @return
     */
    SysUser findById(Integer id);

    /**
     * 用户分配角色
     * @param userId
     * @param roleIds
     */
    void addRoleToUser(Integer userId, Integer[] roleIds);
}
