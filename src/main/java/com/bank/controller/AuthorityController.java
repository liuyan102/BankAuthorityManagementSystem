package com.bank.controller;

import com.bank.pojo.Authority;
import com.bank.result.Result;
import com.bank.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
    权限
 */

@Controller
public class AuthorityController {
    @Autowired
    private AuthorityService authorityService;

    @PostMapping("/addAuthority")
    @ResponseBody
    public Result add(@RequestBody List<Authority> authorityList){ //新增渠道权限
        Result result;
        int add = authorityService.add(authorityList); //新增
        if(add == 1){
            result = new Result(1,"权限修改成功",authorityList);
        }else{
            result = new Result(0,"权限修改失败",null);
        }
        return result;
    }

    @PostMapping("/deleteAuthority")
    @ResponseBody
    public Result delete(@RequestParam String channelCode){ //删除渠道权限
        Result result;
        int delete = authorityService.delete(channelCode); //删除
        if(delete == 1) {
            result = new Result(1, "权限修改成功", null);
        }else{
            result = new Result(0,"权限修改失败",null);
        }
        return result;
    }

    @PostMapping("/queryBusinessByChannel")
    @ResponseBody
    public Result queryByChannelWithBusiness(@RequestParam String channelCode,String businessCode){ //获取所有业务和已开通业务
        List<Authority> authorityList = authorityService.queryByChannelWithBusiness(channelCode,businessCode);//获取
        Result result;
        if(authorityList != null){
            result = new Result(1,"数据获取成功",authorityList);
        }else {
            result = new Result(0,"数据获取失败",null);
        }
        return result;
    }
}
