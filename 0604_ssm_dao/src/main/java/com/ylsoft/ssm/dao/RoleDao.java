package com.ylsoft.ssm.dao;

import com.ylsoft.ssm.domain.Permission;
import com.ylsoft.ssm.domain.Role;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleDao {

    // 根据用户用户的id，在关联表中查询出所有对应的角色,得到多的role角色，下面参数userId就是users的id，这就可以实现多对多的查询，角色里有用户List，用户里有角色List
    // 角色role里面还封装了权限属性，也是1对多关系所以需要再把权限的属性也加上
    @Select("select * from role where id in (select roleid from users_role where userid = #{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "roleName", property = "roleName"),
            @Result(column = "roleDesc", property = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "com.ylsoft.ssm.dao.PermissionDao.findPermissionByRoleId"))
    })
    public List<Role> findRoleByUserId(String userId);

    // 查询所有角色
    @Select("select * from role")
    List<Role> findAll();


    // 查询单个角色的权限
    @Select("select * from role where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "rolename", property = "roleName"),
            @Result(column = "roledesc", property = "roleDesc"),
            @Result(column = "id", property = "permissions", javaType = java.util.List.class, many =
            @Many(select = "com.ylsoft.ssm.dao.PermissionDao.findPermissionByRoleId"))
    })
    Role findById(String id);

    // 用户添加角色的时候查询出来当前可以添加的角色信息
    @Select("select * from  role where id not in (select roleid from users_role where userid = #{id} )")
    List<Role> findOtherRole(String id);



    // 角色添加的时候查询出来可以添加的权限
//    List<Permission> findRoleByIdAndAllPermiss(String id);
}
