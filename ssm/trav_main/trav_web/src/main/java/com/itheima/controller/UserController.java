package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Role;
import com.itheima.domain.SysUser;
import com.itheima.service.IRoleService;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 权限校验注解使用：
 * 方式1：jsr-250
 *   注解：@RolesAllowed("ROLE_ADMIN")
 *   范围：类上使用，方法上使用
 *   定义在类上： 表示访问当前类的所有方法的用户，必须具有角色：ROLE_ADMIN
 * 方式2：springsecurity提供的权限校验注解支持
 *   注解：@Secured("ROLE_ADMIN")
 *   范围：类上使用，方法上使用
 *   定义在类上： 表示访问当前类的所有方法的用户，必须具有角色：ROLE_ADMIN
 *  方式3：SpEL 提供的权限校验注解支持
 *   注解：@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
 *   范围：类上使用，方法上使用
 *   定义在类上： 表示访问当前类的所有方法的用户，必须具有角色：ROLE_ADMIN
 */
@Controller
@RequestMapping("user")
//@RolesAllowed("ROLE_ADMIN")
//@Secured("ROLE_ADMIN")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class UserController {

    // 注入service
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    /**
     * 1. 分页查询
     */
    @RequestMapping("findByPage")
    public ModelAndView findByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "2") int pageSize){

        // 自己实现权限校验？
        // 1.访问用户列表，必须是ROLE_ADMIN角色的用户
        // 2. 查询用户具有的权限；
        // 3. 校验： 用户具有的权限是否包含ROLE_ADMIN，如果包含，就可以访问。


        /**
         * 测试后台获取用户名
         */
        //a. 从当前线程上获取绑定的SecurityContext对象。
        SecurityContext securityContext = SecurityContextHolder.getContext();
        //b. 获取认证器
        Authentication authentication = securityContext.getAuthentication();
        //c. 获取身份对象，其实就是User对象
        User user = (User) authentication.getPrincipal();
        //d. 获取用户名
        System.out.println("用户名：" +user.getUsername());


        //1.1 查询产品
        PageInfo<SysUser> pageInfo = userService.findByPage(pageNum,pageSize);
        //1.2 返回结果
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }
    /**
     * 2. 添加
     */
    @RequestMapping("save")
    public String save(SysUser user){
        userService.save(user);
        return "redirect:/user/findByPage";
    }

    /**
     * 3. 用户详情，根据用户主键查询用户、角色、权限信息
     */
    @RequestMapping("findById")
    public ModelAndView findById(Integer id){
        // 查询产品
        SysUser user = userService.findById(id);
        // 返回
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user-show");
        mv.addObject("user",user);
        return mv;
    }

    /**
     * 4.进入用户角色授权页面 (给用户添加角色或取消用户的角色)
     */
    @RequestMapping("toUserRole")
    public ModelAndView toUserRole(Integer id){
        //4.1 根据用户id查询用户
        SysUser user = userService.findById(id);

        //4.2 获取用户已经具有的角色 (为了再页面默认选中复选框)
        List<Role> userRoles = user.getRoles();
        // 定义角色字符串，保存用户的所有角色
        String roleStr = "";
        if (userRoles != null && userRoles.size()>0){
            for (Role role : userRoles){
                roleStr += role.getRoleName() + ",";
            }
        }

        //4.3 查询所有角色
        List<Role> roleList = roleService.findAll();

        //4.4 返回
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user-role-add");
        mv.addObject("user",user);
        mv.addObject("roleStr",roleStr);
        mv.addObject("roleList",roleList);
        return mv;
    }

    /**
     * 5. 给用户添加角色
     */
    @RequestMapping("addRoleToUser")
    public String addRoleToUser(Integer userId,
                                @RequestParam(value = "ids",required = false) Integer[] roleIds){

        // 给用户分配角色
        userService.addRoleToUser(userId,roleIds);
        return "redirect:/user/findByPage";
    }

}
















