package com.hp.controller;

import com.hp.bean.Menu;
import com.hp.bean.MenuVo;
import com.hp.bean.User;
import com.hp.constant.SysConstant;
import com.hp.service.MenuService;
import com.hp.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;
    //左侧菜单
    @RequestMapping("/loadIndexleftMenuJson")
    public List<TreeNode> loadIndexleftMenuJson(Menu m){
        //得到当前登陆的用户
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        List<Menu> menuList =null;
        m.setAvailable(1); //查询所有可用的
        //用户的身份类型：type 1，超级管理员,2，系统用户
        if(user.getType()== SysConstant.USER_TYPE_SUPER){
            //超级管理员
            menuList = menuService.findAll(m);
        }
        //普通用户
            ArrayList<TreeNode> nodes = new ArrayList<>();
            for(Menu menu:menuList){
                int id = menu.getId();
                int pid = menu.getPid();
                String title = menu.getTitle();
                String icon = menu.getIcon();
                String href = menu.getHref();
               Boolean spread = menu.getSpread()==SysConstant.SPREAD_TRUE?true:false;
                String target = menu.getTarget();
                //添加到list集合
                nodes.add(new TreeNode(id,pid,title,icon,href,spread,target));
            }
            //一级分类
        Integer topPid;
        return TreeNodeBuilder.builder(nodes,topPid=SysConstant.ONE_MENU);
    }
    //左侧菜单管理
    @RequestMapping("/loadMenuManagerLeftTreeJson")
    public DataGridView loadMenuManagerLeftTreeJson(Menu m){
        //查询可用的--》Available
        m.setAvailable(SysConstant.AVAILABLE_TRUE);
        List<Menu> menuList = menuService.findAll(m);
        List<TreeNode> nodes = new ArrayList<>();
        for(Menu menu:menuList){
            int id = menu.getId();
            int pid = menu.getPid();
            String title = menu.getTitle();
            String icon = menu.getIcon();
            String href = menu.getHref();
            Boolean spread = menu.getSpread()==SysConstant.ONE_MENU?true:false;
            String target = menu.getTarget();
            //添加到list集合
            nodes.add(new TreeNode(id,pid,title,icon,href,spread,target));
        }
        return new DataGridView(nodes);
    }
    //右边菜单查询--->loadAllMenu
    @RequestMapping("/loadAllMenu")
    public DataGridView loadAllMenu(MenuVo mv){

        return menuService.findAllByPage(mv);
    }
    //添加菜单
    @RequestMapping("/addMenu")
    public ResultObj addMenu(Menu menu){
        try {
            //添加成功
            menuService.addMenu(menu);
            return ResultObj.ADD_SUCCESS;//添加成功的提示
        }catch(Exception ex){
            ex.printStackTrace();
            return ResultObj.ADD_ERROR;//添加失败
        }
    }
    //修改菜单
    @RequestMapping("/updateMenu")
    public ResultObj updateMenu(Menu menu){
        try {
            //修改成功
            menuService.updateMenu(menu);
            return ResultObj.UPDATE_SUCCESS; //修改成功提示
        }catch (Exception ex){
            //修改失败
            ex.printStackTrace();
            return ResultObj.UPDATE_ERROR; //修改失败提示
        }
    }
    //根据pid查询子节点
    @RequestMapping("/checkMenuHasChildren")
    public  ResultObj  checkMenuHasChildren(int id) { //int id-->与前端后缀需要携带的name一致id==pid(q前者可自定义)
        int count = menuService.findByPid(id);
            if (count > 0) {
                return ResultObj.STATUS_TRUE;
            }else{
                //无子节点(子菜单)
                return ResultObj.STATUS_FALSE;
            }
    }
    //伪删除菜单列表
    @RequestMapping("/deleteMenu")
    public ResultObj deleteMenu(int id){
        try {
            //删除成功
            menuService.deleteMenu(id);
            return ResultObj.DELETE_SUCCESS; //提示删除成功
        }catch (Exception ex){
            //删除失败
            ex.printStackTrace();
            return ResultObj.DELETE_ERROR; //提示删除失败
        }
    }
}
