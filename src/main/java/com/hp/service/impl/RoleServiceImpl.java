package com.hp.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hp.bean.MenuVo;
import com.hp.bean.Role;
import com.hp.mapper.RoleMapper;
import com.hp.service.RoleService;
import com.hp.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    //查询所有
    @Override
    public List<Role> findAll(Role role) {
        return roleMapper.findAll(role);
    }
  /*  //分页方法
    public DataGridView findByPage(Role role){

        Page<MenuVo> pages = PageHelper.startPage(role.getPage(), role.getLimit());


        List<Role> roleList = roleMapper.findAll(role);

        return  new DataGridView(pages.getTotal(),roleList);
    }*/
}
