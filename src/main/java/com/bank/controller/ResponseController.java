package com.bank.controller;

import com.bank.pojo.Response;
import com.bank.result.Result;
import com.bank.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
    交易响应码
 */

@Controller
public class ResponseController {
    @Autowired
    private ResponseService responseService;

    @PostMapping("/addResponse")
    @ResponseBody
    public Result add(@RequestBody Response response){ //新增响应码
        Response response1 = responseService.queryByCode(response.getResponseCode()); //查询响应码是否存在
        Result result;
        if(response1 != null){ //响应码已存在
            result = new Result(-1,"响应码已存在",null);
            return result; //返回
        }
        int add = responseService.add(response); //添加响应码
        if(add == 1){
            result = new Result(1,"新建成功",response);
        }else {
            result = new Result(0,"新建失败",null);
        }
        return result;
    }

    @PostMapping("/deleteResponse")
    @ResponseBody
    public Result delete(@RequestBody Response response){ //删除响应码
        int delete = responseService.delete(response.getResponseCode()); //删除
        Result result;
        if(delete == 1){
            result = new Result(1,"删除成功",response);
        }else {
            result = new Result(0,"删除失败",null);
        }
        return result;
    }

    @PostMapping("/deleteAllResponse")
    @ResponseBody
    public Result deleteAll(@RequestBody String[] responseCodes){ //批量删除响应码
        int delete = responseService.deleteAll(responseCodes); //删除
        Result result;
        if(delete == 1){
            result = new Result(1,"删除成功",responseCodes);
        }else {
            result = new Result(0,"删除失败",null);
        }
        return result;
    }

    @PostMapping("/updateResponse")
    @ResponseBody
    public Result update(@RequestBody Response response){ //修改响应码
        int update = responseService.update(response); //修改
        Result result;
        if(update ==1){
            result = new Result(1,"修改成功",response);
        }else {
            result = new Result(0,"修改失败",null);
        }
        return result;
    }

    @PostMapping("/queryAllResponse")
    @ResponseBody
    public Result queryAll(){ //获取所有响应码
        List<Response> responseList = responseService.queryAll(); //获取
        Result result;
        if(responseList != null){
            result = new Result(1,"数据获取成功",responseList);
        }else {
            result = new Result(0,"数据获取失败",null);
        }
        return result;
    }

    @PostMapping("/queryResponseByKT")
    @ResponseBody
    public Result queryByKT(@RequestParam String key, int responseType){ //按关键字、类型查询响应码
        List<Response> responseList = responseService.queryByKT(key,responseType); //查询
        Result result;
        if(responseList != null){
            result = new Result(1,"查询成功",responseList);
        }else {
            result = new Result(0,"查询为空",null);
        }
        return result;
    }
}
