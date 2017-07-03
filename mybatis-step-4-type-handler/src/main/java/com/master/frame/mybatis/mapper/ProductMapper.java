package com.master.frame.mybatis.mapper;

import com.master.frame.mybatis.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * When use the package scan for mappers, need to annotate the mapper class with sql
 * <br>
 * and remove the *Mapper.xml
 */
public interface ProductMapper {

    /**
     * If the column name and the fields' name not same<br>
     * Need to specify the name to be same as the pojos' fields, use as....<br>
     * Otherwise, can't map the column to the fields
     */
    @Select(value = "select id, category, buy_date as buyDate from mybatis_product_tbl")
    List<Product> load();
    @Insert(value = "insert into mybatis_product_tbl(category,buy_date) values(#{category},#{buyDate})")
    int add(Product product);
    @Update(value = "create table mybatis_product_tbl (\n" +
            "        id int not null auto_increment,\n" +
            "        category varchar(20) not null,\n" +
            "        buy_date datetime,\n" +
            "        primary key (id))")
    void createProductTbl();
    @Update(value = "drop table if exists mybatis_product_tbl")
    void dropIfExistProductTbl();

}