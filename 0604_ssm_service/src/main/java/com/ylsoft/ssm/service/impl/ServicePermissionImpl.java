package com.ylsoft.ssm.service.impl;

import com.ylsoft.ssm.dao.PermissionDao;
import com.ylsoft.ssm.domain.Permission;
import com.ylsoft.ssm.service.ServicePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicePermissionImpl implements ServicePermission {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findOtherPermission(String id) {
        return permissionDao.findOtherPermission(id);
    }

    // 报错角色和权限
    @Override
    public void save(String roleId, String[] Permissionid) {
        for (String permissionid : Permissionid) {
            permissionDao.save(roleId, permissionid);
        }

    }

    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    @Override
    public Permission findById(String id) throws Exception {
        return permissionDao.findById(id);
    }

    @Override
    public void deletePermission(String id) {
        permissionDao.DeleteById(id);
    }

    @Override
    public void savePermission(Permission permission) {
        permissionDao.savePermission(permission);
    }


}
