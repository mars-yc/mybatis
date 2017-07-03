package com.master.frame.mybatis.mapper;

import com.master.frame.mybatis.domain.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface RoleMapper {

    @Update(value = "drop table if exists mybatis_role_tbl")
    void dropIfExistRoleTbl();

    @Update(value = "create table mybatis_role_tbl (\n" +
            "        id int not null auto_increment,\n" +
            "        name varchar(20) not null,\n" +
            "        primary key (id))")
    void createRoleTbl();

    @Select(value = "select * from mybatis_role_tbl")
    List<Role> load();

    @Insert(value = "insert into mybatis_role_tbl(name) values(#{name})")
    int add(Role role);

}