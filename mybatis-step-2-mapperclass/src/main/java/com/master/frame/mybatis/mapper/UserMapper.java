package com.master.frame.mybatis.mapper;

import com.master.frame.mybatis.domain.User;

import java.util.List;

public interface UserMapper {

    List<User> load();

    User findUserByUserName(String username);

    int add(User user);

    int delete(User user);

    int update(User user);

    void createUserTbl();

    void dropIfExistUserTbl();

}