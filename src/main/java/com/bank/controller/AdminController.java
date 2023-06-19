package com.bank.controller;

import com.bank.pojo.Admin;
import com.bank.result.Result;
import com.bank.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/*
    管理员
 */

@Controller
@CrossOrigin
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/adminLogin")
    @ResponseBody
    public Result login(@RequestBody Admin admin){ //管理员登录验证
        Admin admin1 = adminService.login(admin.getUsername(),admin.getPassword()); //验证
        Result result;
        if(admin1!=null){
            result = new Result(1,"登陆成功",admin1);
        } else {
            result = new Result(0,"用户名或密码错误",admin);
        }
        return result;
    }

}
