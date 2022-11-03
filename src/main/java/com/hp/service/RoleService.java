package com.hp.service;

import com.hp.bean.MenuVo;
import com.hp.bean.Role;
import com.hp.utils.DataGridView;

import java.util.List;

public interface RoleService {
    //查询所有
    List<Role> findAll(Role role);
    //分页查询
    /*DataGridView findByPage(Role role);*/
}
