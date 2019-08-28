package com.ylsoft.ssm.service;

import com.ylsoft.ssm.domain.Role;

import java.util.List;

public interface ServiceRole {

    public List<Role> findAll() throws Exception;

    Role findById(String id) throws Exception;

    List<Role> findOtherRole(String id) throws Exception;
}
