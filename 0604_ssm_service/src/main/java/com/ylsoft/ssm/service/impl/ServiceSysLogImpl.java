package com.ylsoft.ssm.service.impl;

import com.ylsoft.ssm.dao.SysLogDao;
import com.ylsoft.ssm.domain.SysLog;
import com.ylsoft.ssm.service.ServiceSysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceSysLogImpl implements ServiceSysLog {

    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }
}
