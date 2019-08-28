package com.ylsoft.ssm.dao;

import com.ylsoft.ssm.domain.Product;

import java.util.List;

public interface ProductDao {
    // 查询所有
    List<Product> findall();

    // 保存
    void save(Product product);
}
