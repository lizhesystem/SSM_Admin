package com.ylsoft.ssm.dao;

import com.ylsoft.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {
    // 查询用户的角色，和角色下的用户，下面的定义字段查询到的结果，roles定义该username关联的角色，1对多关系，把这个roles字段定义ID去RoleDao查对应的角色

    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.ylsoft.ssm.dao.RoleDao.findRoleByUserId"))
    })
    UserInfo findByUsername(String username) throws Exception;

    //  查询用户的角色，以及角色关联的权限，最终封装成UserInfo里有role,role里有user,role里有permission ，permission里有role。
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.ylsoft.ssm.dao.RoleDao.findRoleByUserId"))
    })
    UserInfo findById(String id) throws Exception;

    // 查询所有用户
    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    // 新增用户
    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    // 新增用户的角色
    @Insert("insert into USERS_ROLE(userid,roleid) values(#{userid},#{roleid})")
    void saveRole(@Param("userid") String userid, @Param("roleid") String roleid);
}
