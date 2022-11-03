package com.hp.mapper;

import com.hp.bean.Role;

import java.util.List;

public interface RoleMapper {
    //查询所有
    List<Role> findAll(Role role);
}
