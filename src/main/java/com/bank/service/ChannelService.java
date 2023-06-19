package com.bank.service;

import com.bank.pojo.Channel;

import java.util.List;

/*
    渠道信息操作方法
 */

public interface ChannelService {
    String getCode(); //获取渠道编码
    int add(Channel channel); //新增渠道信息
    int delete(String channelCode); //删除渠道信息
    int deleteAll(String[] channelCodes); //批量删除渠道信息
    int update(Channel channel); //修改渠道信息
    List<Channel> queryAll(); //获取所有渠道
    List<Channel> queryByKey(String key); //按关键字拆线呢
    List<Channel> queryByType(int channelType); //按类型查询
    List<Channel> queryByStatus(int channelStatus); //按状态查询
    List<Channel> queryByKTS(String key, int channelType, int channelStatus); //按关键字、类型、状态查找
}
