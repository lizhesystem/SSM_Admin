package com.ylsoft.ssm.service;

import com.ylsoft.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ServiceUser extends UserDetailsService {
    // 查询所有用户
    List<UserInfo> findAll() throws Exception;

    // 查询单个
    UserInfo findById(String id) throws Exception;

    // 保存用户
    void save(UserInfo userInfo) throws Exception;

    // 保存用户的角色
    void saveRole(String userid, List<String> roles) throws Exception;
}
