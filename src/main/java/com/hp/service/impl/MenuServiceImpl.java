package com.hp.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hp.bean.Menu;
import com.hp.bean.MenuVo;
import com.hp.mapper.MenuMapper;
import com.hp.service.MenuService;
import com.hp.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<Menu> findAll(Menu menu) {
        return menuMapper.findAll(menu);
    }

    @Override
    public DataGridView findAllByPage(MenuVo menuVo) {
        //分页方法
        Page<MenuVo> pages = PageHelper.startPage(menuVo.getPage(), menuVo.getLimit());

        List<Menu> menuList = menuMapper.findAll(menuVo);


        return new DataGridView(pages.getTotal(),menuList);
    }
    //添加
    @Override
    public void addMenu(Menu menu) {
        menuMapper.addMenu(menu);
    }
    //修改
    @Override
    public void updateMenu(Menu menu) {
        menuMapper.updateMenu(menu);
    }
    //根据pid查询子节点
    @Override
    public int findByPid(int pid) {
        return menuMapper.findByPid(pid);
    }

    //伪删除
    @Override
    public void deleteMenu(int id) {
    menuMapper.deleteMenu(id);
    }
}
