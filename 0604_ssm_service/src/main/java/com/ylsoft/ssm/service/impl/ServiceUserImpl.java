package com.ylsoft.ssm.service.impl;

import com.ylsoft.ssm.dao.UserDao;
import com.ylsoft.ssm.domain.Role;
import com.ylsoft.ssm.domain.UserInfo;
import com.ylsoft.ssm.service.ServiceUser;
import com.ylsoft.ssm.utils.BCryptPasswordEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class ServiceUserImpl implements ServiceUser {

    @Autowired
    private UserDao userDao;

    @Override
    // 重写loadUserByUsername方法，参数就是请求过来的用户名默认的
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 处理自己查到的userinfo对象封装成userdetails，使用加密后去掉密码的{noop}
        //  User user=new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));

        User user = new User(userInfo.getUsername(), userInfo.getPassword(),
                userInfo.getStatus() == 0 ? false : true, true, true, true, getAuthority(userInfo.getRoles()));

        return user;
    }

    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        ArrayList<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }

    // 查询所有用户
    @Override
    public List<UserInfo> findAll() throws Exception {
        return userDao.findAll();
    }

    // 查询单个用户详情
    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }

    // 保存用户
    @Override
    public void save(UserInfo userInfo) throws Exception {
        String pwd = userInfo.getPassword();
        userInfo.setPassword(BCryptPasswordEncoderUtils.encodePassword(pwd));
        userDao.save(userInfo);
    }

    // 新增保存用户的角色
    @Override
    public void saveRole(String userid, List<String> roles) throws Exception {
        for (String role : roles) { userDao.saveRole(userid, role);
        }
    }

}
