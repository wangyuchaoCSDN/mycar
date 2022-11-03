package com.hp.service;

import com.hp.bean.Menu;
import com.hp.bean.MenuVo;
import com.hp.utils.DataGridView;

import java.util.List;

public interface MenuService {
    //查询所有
    List<Menu> findAll(Menu menu);
    //分页查询
    DataGridView findAllByPage(MenuVo menuVo);
    //添加
    void addMenu(Menu menu);
    //修改
    void updateMenu(Menu menu);
    //根据pid查询子节点
    int findByPid(int pid);
    //伪删除
    void deleteMenu(int id);
}
