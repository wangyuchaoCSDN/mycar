package com.hp.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.hp.bean.User;
import com.hp.constant.SysConstant;
import com.hp.service.UserService;
import com.hp.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    //显示登录页面
    @RequestMapping("/showLogin")
    public String showLogin(){
        return "system/main/login";
    }
    //登录
    @RequestMapping("/login")
    public String login(User user, String code, Model model){
        //从session中取出生成的验证码
        String s_code = WebUtils.getHttpSession().getAttribute("code").toString();
        User us =  userService.login(user);
        //判断输入的验证码和生成的验证码是否一致
       if(code.equals(s_code)){

           if(us!=null){
               //登录成功
               //放入用户信息到session
               WebUtils.getHttpSession().setAttribute("user",us);

               return "system/main/index";
           }else{
               //登陆失败
               //用户名或密码错误
               model.addAttribute("error",SysConstant.USER_LOGIN_ERROR_MSG);
               return "system/main/login";
           }
       }else {
           //登陆失败
           //验证码错误
           model.addAttribute("error",SysConstant.USER_LOGIN_CODE_ERROR_MSG);
           return "system/main/login";
       }
    }
    //生成验证码
    @RequestMapping("/getCode")
    public void getCode(HttpSession session, HttpServletResponse response) throws IOException {
        //定义图形验证码的长和宽
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(116, 36, 4, 5);
        //放入session
        session.setAttribute("code",circleCaptcha.getCode());
        //通过文件流输入
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(circleCaptcha.getImage(),"JPEG",outputStream);

    }
}
