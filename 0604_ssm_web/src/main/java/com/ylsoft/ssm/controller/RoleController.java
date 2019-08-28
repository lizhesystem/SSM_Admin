package com.ylsoft.ssm.controller;

import com.ylsoft.ssm.dao.RoleDao;
import com.ylsoft.ssm.domain.Permission;
import com.ylsoft.ssm.domain.Role;
import com.ylsoft.ssm.service.ServicePermission;
import com.ylsoft.ssm.service.ServiceRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private ServiceRole serviceRole;

    @Autowired
    private ServicePermission servicePermission;

    private ModelAndView mv = new ModelAndView();

    // 查询所有角色
    // @RolesAllowed("ADMIN") 表示只能ADMIN用户访问这个方法
    @RequestMapping("/findAll.do")
    public ModelAndView findall() throws Exception {
        List<Role> Rlist = serviceRole.findAll();
        mv.addObject("roleList", Rlist);
        mv.setViewName("role-list");
        return mv;
    }

    // 查询单个角色
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        Role role = serviceRole.findById(id);
        mv.addObject("role", role);
        mv.setViewName("role-show");
        return mv;
    }

    // 添加角色之前查询可以添加的角色
    @RequestMapping("/findRoleByIdAndAllPermiss.do")
    public ModelAndView findRoleByIdAndAllPermiss(String id) throws Exception {
        Role role = serviceRole.findById(id);
        List<Permission> permission = servicePermission.findOtherPermission(id);
        mv.addObject("role", role);
        mv.addObject("permissionList", permission);
        mv.setViewName("role-permission-add");
        return mv;
    }

    // 添加角色
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(value = "roleId", required = true) String roleId,
                                      @RequestParam(value = "ids", required = true) String[] ids) throws Exception {
        servicePermission.save(roleId, ids);
        return "redirect:findAll.do";
    }
}
