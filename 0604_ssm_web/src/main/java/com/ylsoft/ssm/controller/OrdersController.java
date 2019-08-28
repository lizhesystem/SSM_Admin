package com.ylsoft.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ylsoft.ssm.domain.Orders;
import com.ylsoft.ssm.service.ServiceOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 订单的controller
 */
@Controller
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private ServiceOrders serviceOrders;

    @RequestMapping("/findall.do")
    @Secured("ROLE_ADMIN")
    // 使用RequestParam设置传入的参数默认值，page=当前页，size=每页显示条数。。
    public ModelAndView findal(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                               @RequestParam(name = "size", required = true, defaultValue = "5") Integer size
    ) throws Exception {
        ModelAndView mv = new ModelAndView();

        // 查询的时候传入当前页码
        List<Orders> lists = serviceOrders.findall(page, size);

        // 得到的结果封装到PageInfo，这是个分页bean,
        PageInfo pageInfo = new PageInfo(lists);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        // 查询到orders对象
        Orders orders = serviceOrders.findById(id);
        mv.addObject("orders", orders);
        mv.setViewName("orders-show");
        return mv;
    }

}
