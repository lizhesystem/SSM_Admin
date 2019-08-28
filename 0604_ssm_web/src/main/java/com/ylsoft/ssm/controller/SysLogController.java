package com.ylsoft.ssm.controller;

import com.ylsoft.ssm.domain.SysLog;
import com.ylsoft.ssm.service.ServiceSysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private ServiceSysLog serviceSysLog;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<SysLog> Slisg  = serviceSysLog.findAll();
        mv.addObject("sysLogs",Slisg);
        mv.setViewName("syslog-list");
        return mv;
    }
}
