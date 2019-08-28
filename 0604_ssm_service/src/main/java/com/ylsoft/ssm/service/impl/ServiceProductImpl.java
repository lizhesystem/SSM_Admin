package com.ylsoft.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ylsoft.ssm.dao.ProductDao;
import com.ylsoft.ssm.domain.Product;
import com.ylsoft.ssm.service.ServiceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Service
public class ServiceProductImpl implements ServiceProduct  {

    @Autowired
    private ProductDao productDao;

    //    查询所有订单
    @Override
    public List<Product> findall(int size, int page) throws Exception {
        PageHelper.startPage(size, page);
        return productDao.findall();
    }

    //    新增产品
    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }
}
