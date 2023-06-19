package com.bank.controller;

import com.bank.pojo.Channel;
import com.bank.result.Result;
import com.bank.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
    渠道信息
 */

@Controller
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    @PostMapping("/getChannelCode")
    @ResponseBody
    public Result getCode(){ //获取渠道
        String channelCode = channelService.getCode(); //获取
        Result result = new Result(1,"获取编码成功",channelCode);
        return result;
    }

    @PostMapping("/addChannel")
    @ResponseBody
    public Result add(@RequestBody Channel channel){ //新增渠道
        int add = channelService.add(channel); //新增
        Result result;
        if(add == 1){
            result = new Result(1,"新建成功",channel);
        }else {
            result = new Result(0,"新建失败",null);
        }
        return result;
    }

    @PostMapping("/deleteChannel")
    @ResponseBody
    public Result delete(@RequestBody Channel channel){ //删除渠道
        int delete = channelService.delete(channel.getChannelCode()); //删除
        Result result;
        if(delete == 1){
            result = new Result(1,"删除成功",channel);
        }else {
            result = new Result(0,"删除失败",null);
        }
        return result;
    }

    @PostMapping("/deleteAllChannel")
    @ResponseBody
    public Result deleteAll(@RequestBody String[] channelCodes){ //批量删除
        int delete = channelService.deleteAll(channelCodes); //删除
        Result result;
        if(delete == 1){
            result = new Result(1,"删除成功",channelCodes);
        }else {
            result = new Result(0,"删除失败",null);
        }
        return result;
    }

    @PostMapping("/updateChannel")
    @ResponseBody
    public Result update(@RequestBody Channel channel){ //修改渠道
        int update = channelService.update(channel); //修改
        Result result;
        if(update ==1){
            result = new Result(1,"修改成功",channel);
        }else {
            result = new Result(0,"修改失败",null);
        }
        return result;
    }

    @PostMapping("/queryAllChannel")
    @ResponseBody
    public Result queryAll(){ //查询所有渠道
        List<Channel> channelList = channelService.queryAll();//获取
        Result result;
        if(channelList != null){
            result = new Result(1,"数据获取成功",channelList);
        }else {
            result = new Result(0,"数据获取失败",null);
        }
        return result;
    }

    @PostMapping("/queryChannelByKey")
    @ResponseBody
    public Result queryByKey(@RequestParam String key){ //关键字查询
        List<Channel> channelList = channelService.queryByKey(key);
        Result result;
        if(channelList != null){
            result = new Result(1,"查询成功",channelList);
        }else {
            result = new Result(0,"查询为空",null);
        }
        return result;
    }

    @PostMapping("/queryChannelByType")
    @ResponseBody
    public Result queryByType(@RequestParam int channelType){ //类型查询
        List<Channel> channelList = channelService.queryByType(channelType);
        Result result;
        if(channelList != null){
            result = new Result(1,"查询成功",channelList);
        }else {
            result = new Result(0,"查询为空",null);
        }
        return result;
    }

    @PostMapping("/queryChannelByStatus")
    @ResponseBody
    public Result queryByStatus(@RequestParam int channelStatus){ //状态查询
        List<Channel> channelList = channelService.queryByStatus(channelStatus);
        Result result;
        if(channelList != null){
            result = new Result(1,"查询成功",channelList);
        }else {
            result = new Result(0,"查询为空",null);
        }
        return result;
    }

    @PostMapping("/queryChannelByKTS")
    @ResponseBody
    public Result queryByKTS(@RequestParam String key,int channelType,int channelStatus){ //按关键字、类型、状态查询
        List<Channel> channelList = channelService.queryByKTS(key,channelType,channelStatus);
        Result result;
        if(channelList != null){
            result = new Result(1,"查询成功",channelList);
        }else {
            result = new Result(0,"查询为空",null);
        }
        return result;
    }
}
