package com.ylsoft.ssm.service;

import com.ylsoft.ssm.domain.Orders;

import java.util.List;

public interface ServiceOrders {
    List<Orders> findall(int size,int page) throws Exception;

    Orders findById(String id) throws Exception;
}
