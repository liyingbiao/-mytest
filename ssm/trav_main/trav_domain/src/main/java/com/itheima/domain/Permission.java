package com.itheima.domain;

public class Permission {
    // 编号
    private Long id;
    // 权限名称
    private String permissionName;
    // 权限的链接地址。除了一级菜单（系统管理、基础数据）外其他菜单都有链接地址。
    private String url;
    // 权限的父菜单id(自关联)
    private Long pid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
}
