package com.ylsoft.ssm.controller;


import com.ylsoft.ssm.domain.Permission;
import com.ylsoft.ssm.service.ServicePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private ServicePermission servicePermission;

    //    查询所有的权限
    @RequestMapping("/findAll.do")
    public ModelAndView findall() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Permission> plist = servicePermission.findAll();
        mv.addObject("permissionList", plist);
        mv.setViewName("permission-list");
        return mv;
    }

    //    查询单个角色权限
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Permission permission = servicePermission.findById(id);
        mv.addObject("permission", permission);
        mv.setViewName("permission-show");
        return mv;
    }

    //    保存权限
    @RequestMapping("/save.do")
    public String save(Permission permission) throws Exception {
        servicePermission.savePermission(permission);
        return "redirect:findAll.do";
    }

    // 删除权限
    @RequestMapping("/deletePermission.do")
    public String deletePermission(String id) throws Exception {
        servicePermission.deletePermission(id);
        return "redirect:findAll.do";
    }


}
