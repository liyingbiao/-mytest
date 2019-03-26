package com.itheima.dao;

import com.itheima.domain.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IRoleDao {
    /**
     * 查询全部
     */
    @Select("select * from sys_role")
    List<Role> findAll();

    /**
     * 新增
     */
    @Insert("insert into sys_role values(seq_role.nextval," +
            "#{roleName},#{roleDesc})")
    void save(Role role);

    /**
     * 根据用户的id，查询角色
     */
    @Select("select r.* from sys_role r " +
            "inner join sys_user_role ur on r.id=ur.roleid where ur.userid=#{userId}")
    @Results({
            /*1.封装角色信息*/
            @Result(id = true,property = "id",column = "id"),
            /*2.封装权限信息*/
            @Result(property = "permissions",column = "id",javaType = List.class,
                many = @Many(select = "com.itheima.dao.IPermissionDao.findPermissionsByRoleId",
                        fetchType = FetchType.LAZY)
            )
    })
    List<Role> findRolesByUserId(int userId);
}














