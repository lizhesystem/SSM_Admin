package com.ylsoft.ssm.controller;

import com.ylsoft.ssm.dao.RoleDao;
import com.ylsoft.ssm.domain.Role;
import com.ylsoft.ssm.domain.UserInfo;
import com.ylsoft.ssm.service.ServiceProduct;
import com.ylsoft.ssm.service.ServiceRole;
import com.ylsoft.ssm.service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private ServiceUser serviceUser;

    @Autowired
    private ServiceRole serviceRole;

    // 查询所有
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> lists = serviceUser.findAll();
        mv.addObject("userList", lists);
        mv.setViewName("user-list");
        return mv;
    }

    // 查询单个
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userinfo = serviceUser.findById(id);
        mv.addObject("user", userinfo);
        mv.setViewName("user-show");
        return mv;
    }

    //保存数据
    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) throws Exception {
        serviceUser.save(userInfo);
        return "redirect:findAll.do";
    }

    // 添加角色，先查出来这个用户不包含的角色
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo user = serviceUser.findById(id);
        List<Role> roles = serviceRole.findOtherRole(id);
        mv.addObject("roleList", roles);
        mv.addObject("user", user);
        mv.setViewName("user-role-add");
        return mv;
    }

    // 选中角色，进行添加。
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(value = "userId", required = true) String userid,
                                @RequestParam(value = "ids", required = true) List<String> roles) throws Exception {

        serviceUser.saveRole(userid,roles);
        return "redirect:findAll.do";
    }
}
