package com.bank.service.impl;

import com.bank.mapper.AdminMapper;
import com.bank.pojo.Admin;
import com.bank.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
    管理员操作方法实现
 */

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(String username,String password){ //登录验证
        Admin admin = adminMapper.queryByUsername(username);
        if(admin!=null && password.equals(admin.getPassword()))
            return admin; //登陆成功
        else
            return null; //用户名或密码错误
    }
}
