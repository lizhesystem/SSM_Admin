package com.ylsoft.ssm.dao;

import com.ylsoft.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysLogDao {

    @Insert(" insert into SYSLOG (visitTime,username,ip,url,executionTime,method) values " +
            "(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog);

    @Select("select * from SYSLOG")
    List<SysLog> findAll();
}
