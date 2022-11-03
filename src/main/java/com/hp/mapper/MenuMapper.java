package com.hp.mapper;

import com.hp.bean.Menu;

import java.util.List;

public interface MenuMapper {
    //查询所有
    List<Menu> findAll(Menu menu);
    //添加
    void addMenu(Menu menu);
    //修改
    void updateMenu(Menu menu);
    //根据pid查询子节点
    int findByPid(int pid);
    //伪删除
    void deleteMenu(int id);
}
