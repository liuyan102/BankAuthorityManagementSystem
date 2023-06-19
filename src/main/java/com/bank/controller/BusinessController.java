package com.bank.controller;

import com.bank.pojo.Business;
import com.bank.result.Result;
import com.bank.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
    业务编码
 */

@Controller
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    @PostMapping("/getBusinessCode")
    @ResponseBody
    public Result getCode(){ //获取编码
        String businessCode = businessService.getCode(); //获取
        Result result = new Result(1,"获取编码成功",businessCode);
        return result;
    }

    @PostMapping("/addBusiness")
    @ResponseBody
    public Result add(@RequestBody Business business){ //新增编码
        int add = businessService.add(business); //新增
        Result result;
        if(add == 1){
            result = new Result(1,"新建成功",business);
        }else {
            result = new Result(0,"新建失败",business);
        }
        return result;
    }

    @PostMapping("/deleteBusiness")
    @ResponseBody
    public Result delete(@RequestBody Business business){ //删除编码
        int delete = businessService.delete(business.getBusinessCode()); //删除
        Result result;
        if(delete == 1){
            result = new Result(1,"删除成功",business);
        }else {
            result = new Result(0,"删除失败",business);
        }
        return result;
    }

    @PostMapping("/deleteAllBusiness")
    @ResponseBody
    public Result deleteAll(@RequestBody String[] businessCodes){ //批量删除
        int delete = businessService.deleteAll(businessCodes); //删除
        Result result;
        if(delete == 1){
            result = new Result(1,"删除成功",businessCodes);
        }else {
            result = new Result(0,"删除失败",businessCodes);
        }
        return result;
    }

    @PostMapping("/updateBusiness")
    @ResponseBody
    public Result update(@RequestBody Business business){ //修改
        int update = businessService.update(business); //修改
        Result result;
        if(update ==1){
            result = new Result(1,"修改成功",business);
        }else {
            result = new Result(0,"修改失败",business);
        }
        return result;
    }

    @PostMapping("/queryAllBusiness")
    @ResponseBody
    public Result queryAll(){ //获取所有业务编码
        List<Business> businessList = businessService.queryAll(); //获取
        Result result;
        if(businessList != null){
            result = new Result(1,"数据获取成功",businessList);
        }else {
            result = new Result(0,"数据获取失败",null);
        }
        return result;
    }

    @PostMapping("/queryBusinessByCode")
    @ResponseBody
    public Result queryByCode(@RequestParam String businessCode){ //查询业务编码
        List<Business> businessList = businessService.queryByCode(businessCode); //查询
        Result result;
        if(businessList != null){
            result = new Result(1,"数据获取成功",businessList);
        }else {
            result = new Result(0,"数据获取失败",null);
        }
        return result;
    }

    @PostMapping("/queryBusinessByKTS")
    @ResponseBody
    public Result queryByKTS(@RequestParam String key,int businessType,int businessStatus){ //按关键字、类型、状态查询
        List<Business> businessList = businessService.queryByKTS(key,businessType,businessStatus); //查询
        Result result;
        if(businessList != null){
            result = new Result(1,"查询成功",businessList);
        }else {
            result = new Result(0,"查询为空",null);
        }
        return result;
    }
}
