package com.hp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
public class SysController {
    //跳转到菜单管理
    @RequestMapping("/goMenuManager")
    public String goMenuManager(){
        return "system/menu/menuManager";
    }
    //跳转到菜单管理里面的左边的菜单树里面
    @RequestMapping("/toMenuLeft")
    public String toMenuLeft(){
        return "system/menu/menuLeft";
    }
    //跳转到菜单管理右边的菜单列表
    @RequestMapping("/toMenuRight")
    public String toMenuRight(){
        return "system/menu/menuRight";
    }
    //跳转到角色管理
    @RequestMapping("/toRoleManager")
    public String toRoleManager(){
        return "system/role/roleManager";
    }

}
