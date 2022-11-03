package com.hp.service.impl;

import com.hp.bean.User;
import com.hp.mapper.UserMapper;
import com.hp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User login(User user) {
        //加密密码123456密文,转明文
        String dpwd = DigestUtils.md5DigestAsHex(user.getPwd().getBytes());
        user.setPwd(dpwd);
        return userMapper.login(user);
    }
}
