package com.ylsoft.ssm.service;


import com.ylsoft.ssm.domain.SysLog;

import java.util.List;

public interface ServiceSysLog {
    void save(SysLog sysLog);

    List<SysLog> findAll();
}
