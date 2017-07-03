package com.master.frame.mybatis.service;

import com.master.frame.mybatis.domain.Role;

import java.util.List;

public interface RoleService {

    void add(Role role);
    List<Role> load();

}