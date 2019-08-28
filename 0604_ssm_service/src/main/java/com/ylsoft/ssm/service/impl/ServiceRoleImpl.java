package com.ylsoft.ssm.service.impl;

import com.ylsoft.ssm.dao.RoleDao;
import com.ylsoft.ssm.domain.Role;
import com.ylsoft.ssm.service.ServiceRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceRoleImpl implements ServiceRole {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    @Override
    public Role findById(String id) throws Exception {
        return roleDao.findById(id);
    }

    @Override
    public List<Role> findOtherRole(String id) throws Exception {
        return roleDao.findOtherRole(id);
    }

}
