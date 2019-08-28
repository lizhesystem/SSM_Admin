package com.ylsoft.ssm.service;

import com.ylsoft.ssm.domain.Permission;

import java.util.List;

public interface ServicePermission {
    List<Permission> findOtherPermission(String id);

    void save(String roleId, String[] Permissionid) throws Exception;

    List<Permission> findAll() throws Exception;

    Permission findById(String id) throws  Exception;

    void deletePermission(String id);

    void savePermission(Permission permission);
}
