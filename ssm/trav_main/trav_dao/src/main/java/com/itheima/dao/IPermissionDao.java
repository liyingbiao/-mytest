package com.itheima.dao;

import com.itheima.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {
    /**
     * 查询全部
     */
    @Select("select * from sys_Permission")
    List<Permission> findAll();

    /**
     * 新增
     */
    @Insert("insert into sys_Permission values(SEQ_PERMISSION.nextval," +
            "#{permissionName},#{url},#{pid})")
    void save(Permission permission);

    /**
     * 根据角色id，查询权限
     */
    @Select("select * from sys_permission p inner join sys_role_permission rp " +
            "on p.id=rp.permissionid where rp.roleid=#{roleId}")
    List<Permission> findPermissionsByRoleId(int roleId);
}














