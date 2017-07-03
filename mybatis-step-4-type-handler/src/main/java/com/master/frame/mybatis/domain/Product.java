package com.master.frame.mybatis.domain;

import java.util.Date;

public class Product {

    private Integer id;
    private Category category;
    private Date buyDate;

    public Product() {
    }

    public Product(Category category, Date buyDate) {
        this.category = category;
        this.buyDate = buyDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category=" + category +
                ", buyDate=" + buyDate +
                '}';
    }
}