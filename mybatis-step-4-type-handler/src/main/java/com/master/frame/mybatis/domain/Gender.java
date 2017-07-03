package com.master.frame.mybatis.domain;

public enum Gender {

    MALE("男"), FEMALE("女");

    private String description;

    Gender(String description) {
        this.description = description;
    }

}