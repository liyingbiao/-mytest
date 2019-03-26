package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Role;
import com.itheima.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("role")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class RoleController {

    // 注入service
    @Autowired
    private IRoleService roleService;

    /**
     * 1. 分页查询
     */
    @RequestMapping("findByPage")
    public ModelAndView findByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "2") int pageSize){
        //1.1 查询产品
        PageInfo<Role> pageInfo = roleService.findByPage(pageNum,pageSize);
        //1.2 返回结果
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("role-list");
        return mv;
    }
    /**
     * 2. 添加
     */
    @RequestMapping("save")
    public String save(Role role){
        roleService.save(role);
        return "redirect:/role/findByPage";
    }

}
















