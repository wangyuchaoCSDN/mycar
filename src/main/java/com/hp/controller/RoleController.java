package com.hp.controller;

import com.hp.bean.Role;
import com.hp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    //查询所有
    @RequestMapping("/loadAllRole")
    public List<Role> loadAllRole(Role role){
        System.out.println(roleService.findAll(role));
        return  roleService.findAll(role);
    }
}
