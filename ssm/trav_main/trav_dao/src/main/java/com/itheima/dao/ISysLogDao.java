package com.itheima.dao;

import com.itheima.domain.SysLog;
import org.apache.ibatis.annotations.Insert;

public interface ISysLogDao {

    /**
     * 新增
     */
    @Insert("insert into sys_log values(seq_log.nextval," +
            "#{visitTime},#{username},#{ip},#{method})")
    void save(SysLog sysLog);
}














