package com.itheima.dao;

import com.itheima.domain.SysUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IUserDao {
    /**
     * 根据用户名查询 (查询用户、角色)
     */
    @Select("select * from sys_user where username=#{username}")
    @Results({
            /*1.封装用户信息*/
            @Result(id = true,property = "id",column = "id"),
            /*2.封装角色信息*/
            @Result(property = "roles",column = "id",javaType = List.class,
                    many = @Many(select = "com.itheima.dao.IRoleDao.findRolesByUserId",
                            fetchType = FetchType.LAZY)
            )
    })
    List<SysUser> findByUsername(String username);

    /**
     * 查询全部
     */
    @Select("select * from sys_user")
    List<SysUser> findAll();

    /**
     * 新增用户
     * @param user
     */
    @Insert("insert into sys_user values(seq_user.nextval," +
            "#{username},#{email},#{password},#{phoneNum},#{status})")
    void save(SysUser user);

    /**
     * 根据用户id查询用户、角色、权限
     */
    @Select("select * from sys_user where id=#{id}")
    @Results({
            /*1.封装用户信息*/
            @Result(id = true,property = "id",column = "id"),
            /*2.封装角色信息*/
            @Result(property = "roles",column = "id",javaType = List.class,
                many = @Many(select = "com.itheima.dao.IRoleDao.findRolesByUserId",
                        fetchType = FetchType.LAZY)
            )
    })
    SysUser findById(Integer id);

    /**
     * 根据用户id删除用户角色中间表数据
     * @param userId
     */
    @Delete("delete from sys_user_role where userid=#{userId}")
    void deleteUserRoleByUserId(Integer userId);

    /**
     * 给用户添加角色
     * @param userId 用户id
     * @param roleId 角色id
     */
    @Insert("insert into sys_user_role(userid,roleid)values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
}














