package com.master.frame.mybatis.dao;

import com.master.frame.mybatis.domain.Category;
import com.master.frame.mybatis.domain.Product;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyBatisProductDaoImplTest {

    private static MyBatisProductDaoImpl productDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        InputStream is = Object.class.getResourceAsStream("/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        productDao = new MyBatisProductDaoImpl(sqlSessionFactory);
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test3Add() throws Exception {
        Product product = new Product(Category.COMMODITY, new Date());
        productDao.add(product);
    }

    @Test
    public void test4Load() throws Exception {
        List<Product> list = productDao.load();
        for(Product product : list) {
            System.out.println(product);
        }
    }

    @Test
    public void test2CreateProductTbl() throws Exception {
        productDao.createProductTbl();
    }

    @Test
    public void test1DropIfExistProductTbl() throws Exception {
        productDao.dropIfExistProductTbl();
    }

}