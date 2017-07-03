package com.master.frame.mybatis.domain;

public enum Category {

    COMMODITY("日用品"), OTHER("其他用品");

    private String description;

    Category(String description) {
        this.description = description;
    }

    public static Category getCategory(String description) {
        for(Category category : Category.values()) {
            if (category.getDescription().equals(description)) {
                return category;
            }
        }
        return null;
    }

    public String getDescription() {
        return description;
    }
}