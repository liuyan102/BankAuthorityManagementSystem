package com.bank.service;

import com.bank.pojo.Admin;

/*
    管理员操作方法
 */

public interface AdminService {
    Admin login(String username,String password); //登录验证
}
