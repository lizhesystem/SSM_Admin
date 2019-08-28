package com.ylsoft.ssm.dao;

import com.ylsoft.ssm.domain.Permission;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PermissionDao {
    //关联查询用户角色的权限用到的sql，roleid就是传过来的id，每个角色ID根据这个ID去中间表查关系，
    @Select("select * from PERMISSION where id in(select PERMISSIONID from ROLE_PERMISSION where ROLEID=#{RoleId})")
    List<Permission> findPermissionByRoleId(String RoleId) throws Exception;


    // 查询出来角色权限不包含在关系表的其他权限。
    @Select("select * from PERMISSION where id not in( select PERMISSIONID from ROLE_PERMISSION where roleid = #{id}) ")
    List<Permission> findOtherPermission(String id);

    // 给角色添加权限
    @Insert("insert into ROLE_PERMISSION(ROLEID,PERMISSIONID) values(#{roleId},#{permissionid})")
    void save(@Param("roleId") String roleId, @Param("permissionid") String permissionid);

    // 查询所有的权限
    @Select(" select * from PERMISSION")
    List<Permission> findAll();

    // 查询单个角色权限
    @Select(" select * from PERMISSION where id = #{id}")
    Permission findById(String id);

    @Delete(" delete from PERMISSION where id = #{id}")
    void DeleteById(String id);

    @Insert("insert into PERMISSION(permissionName,url) values(#{permissionName},#{url})")
    void savePermission(Permission permission);
}
