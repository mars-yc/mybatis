package com.master.frame.mybatis.mapper;

import com.master.frame.mybatis.domain.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {

    @Select(value = "select * from mybatis_user_tbl")
    List<User> load();

    @Select(value = "select * from mybatis_user_tbl where username like '%${value}%'")
    User findUserByUserName(String username);

    @Update(value = "insert into mybatis_user_tbl(username,gender,email) values(#{username},#{gender},#{email})")
    int add(User user);

    @Update(value = "delete from mybatis_user_tbl where username=#{username}")
    int delete(User user);

    @Update(value = "update mybatis_user_tbl set gender=#{gender},email=#{email} where username=#{username}")
    int update(User user);

    @Update(value = "create table mybatis_user_tbl (\n" +
            "        username varchar(20) not null,\n" +
            "        gender char(2),\n" +
            "        email varchar(30),\n" +
            "        primary key (username))")
    void createUserTbl();

    @Update(value = "drop table if exists mybatis_user_tbl")
    void dropIfExistUserTbl();

}