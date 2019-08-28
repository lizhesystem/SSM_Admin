package com.ylsoft.ssm.controller;


import com.github.pagehelper.PageInfo;
import com.ylsoft.ssm.domain.Product;
import com.ylsoft.ssm.service.ServiceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * 产品的controller
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ServiceProduct serviceProduct;

    //    查询所有产品
    @RequestMapping("/findall.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findall(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                @RequestParam(name = "size", required = true, defaultValue = "5") Integer size) throws Exception {

        List<Product> lists = serviceProduct.findall(page, size);
        // 得到的结果封装到PageInfo，这是个分页bean,
        PageInfo pageInfo = new PageInfo(lists);

        ModelAndView mv = new ModelAndView();
        mv.addObject("productList", pageInfo);
        mv.setViewName("product-list1");
        return mv;
    }

    //    新增保存产品
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        if (product != null) {
            serviceProduct.save(product);
        }
        return "forward:/0604/findall.do";
    }


}
