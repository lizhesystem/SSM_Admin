package com.ylsoft.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ylsoft.ssm.dao.OrdersDao;
import com.ylsoft.ssm.domain.Orders;
import com.ylsoft.ssm.service.ServiceOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Service
public class ServiceOrdersImpl implements ServiceOrders {

    @Autowired
    private OrdersDao ordersDao;

    @Override
    public List<Orders> findall(int size, int page) throws Exception {
        //分页插件，写在执行SQL方法的前面,接收web端传过来的页码
        PageHelper.startPage(size, page);
        return ordersDao.findall();
    }

    @Override
    public Orders findById(String id) throws Exception {
        return ordersDao.findByid(id);
    }
}
