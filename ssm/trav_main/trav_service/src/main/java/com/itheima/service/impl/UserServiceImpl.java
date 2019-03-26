package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.IUserDao;
import com.itheima.domain.Role;
import com.itheima.domain.SysUser;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

// 注意：加入容器的service的名称一定要与spring-security.xml引用的名称一致
@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // 通过方法返回的UserDetails对象，告诉springsecurity数据库正确的账号密码，以及用户具有的权限。
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1. 根据用户名查询用户
        List<SysUser> list = userDao.findByUsername(username);

        //2. 判断
        if (list == null || list.size() == 0) {
            // 说明用户名不存在
            return null;
        }

        // 获取用户
        SysUser sysUser = list.get(0);

        // 用户具有的角色
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        // 获取用户的角色、遍历
        List<Role> roles = sysUser.getRoles();
        if (roles != null && roles.size()>0){
            for (Role role : roles){
                // 集合中添加角色
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
        }
        //3. 返回UserDetails类型的对象
        // 参数1：数据库中正确的用户名
        // 参数2：数据库中正确的密码
        // 参数3：用户的角色
        UserDetails userDetails = new User(username,sysUser.getPassword(),authorities);
        return userDetails;
    }

    @Override
    public PageInfo<SysUser> findByPage(int pageNum, int pageSize) {
        // PageHelper分页
        PageHelper.startPage(pageNum,pageSize);
        // 调用dao查询全部，并封装分页参数返回
        return new PageInfo<SysUser>(userDao.findAll());
    }

    @Override
    public void save(SysUser user) {
        // 对用户输入的密码加密
        String encodePwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePwd);
        // 保存用户
        userDao.save(user);
    }

    @Override
    public SysUser findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public void addRoleToUser(Integer userId, Integer[] roleIds) {
        //-- 1) 先解除用户角色的关系
        //delete from sys_user_role where userid=1
        //-- 2) 用户添加角色
        //insert into sys_user_role(userid,roleid)values(1,1)

        // 1) 先解除用户角色的关系
        userDao.deleteUserRoleByUserId(userId);

        // 2) 用户添加角色
        if (roleIds != null && roleIds.length > 0){
            for (Integer roleId : roleIds){
                userDao.addRoleToUser(userId,roleId);
            }
        }
    }
}














