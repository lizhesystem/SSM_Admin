package com.ylsoft.ssm.service;

import com.ylsoft.ssm.domain.Product;

import java.util.List;

public interface ServiceProduct {
    List<Product> findall(int size, int page) throws Exception;;

    void save(Product product) throws Exception ;
}
