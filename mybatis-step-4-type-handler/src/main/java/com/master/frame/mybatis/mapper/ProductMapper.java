package com.master.frame.mybatis.mapper;

import com.master.frame.mybatis.domain.Product;

import java.util.List;

public interface ProductMapper {

    List<Product> load();
    int add(Product product);
    void createProductTbl();
    void dropIfExistProductTbl();

}