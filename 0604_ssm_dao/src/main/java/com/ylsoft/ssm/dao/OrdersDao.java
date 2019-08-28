package com.ylsoft.ssm.dao;

import com.ylsoft.ssm.domain.Orders;

import java.util.List;

public interface OrdersDao {
    // 查询所有订单
    public List<Orders> findall();

    Orders findByid(String id);
}
