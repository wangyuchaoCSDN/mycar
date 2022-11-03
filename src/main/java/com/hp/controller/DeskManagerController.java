package com.hp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/desk")
public class DeskManagerController {
    //显示后台首页
    @RequestMapping("/toDeskManager")
    public String toDeskManager(){

        return "system/main/deskManager";
    }
}
