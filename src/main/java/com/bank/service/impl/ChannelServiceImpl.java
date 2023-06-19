package com.bank.service.impl;

import com.bank.mapper.AuthorityMapper;
import com.bank.mapper.ChannelMapper;
import com.bank.mapper.ChannelNumMapper;
import com.bank.pojo.Authority;
import com.bank.pojo.Channel;
import com.bank.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
    渠道操作方法
 */

@Service
@Transactional
public class ChannelServiceImpl implements ChannelService {
    @Autowired
    private ChannelMapper channelMapper;
    @Autowired
    private ChannelNumMapper channelNumMapper;
    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public String getCode(){ //获取编码
        int maxChannelNum = channelNumMapper.queryMax(); //找到编号最大值
        String channelCode = String.format("channel%03d", maxChannelNum+1); //最大值+1，不足三位补0
        return channelCode;
    }

    @Override
    public int add(Channel channel){ //新增渠道
        int result = channelMapper.add(channel); //添加渠道
        int result2 = channelNumMapper.add(channelNumMapper.queryMax()+1); //添加编号
        if(result > 0 && result2 > 0){
            return 1; //成功
        }else {
            return 0; //失败
        }
    }

    @Override
    public int delete(String channelCode){ //删除渠道
        int result = channelMapper.delete(channelCode); //删除渠道
        int channelID = Integer.parseInt(channelCode.substring(7)); //获取编号
        int resultNum = channelNumMapper.delete(channelID); //删除编号
        List<Authority> authorityList = authorityMapper.queryByChannel(channelCode); //获取渠道权限
        int resultAuthority = 0;
        if(authorityList.size()!= 0){ //权限不为空
            resultAuthority = authorityMapper.deleteChannel(channelCode); //删除权限
        }else{
            resultAuthority = 1;
        }
        if(result > 0 && resultNum > 0 && resultAuthority > 0){
            return 1; //成功
        }else {
            return 0; //失败
        }
    }

    @Override
    public int deleteAll(String[] channelCodes){ //批量删除渠道
        int result = channelMapper.deleteAll(channelCodes); //批量删除渠道
        int []channelIDs = new int[channelCodes.length];
        for(int i=0;i<channelCodes.length;i++){ //获取编号
            channelIDs[i] = Integer.parseInt(channelCodes[i].substring(7));
        }
        int resultNum = channelNumMapper.deleteAll(channelIDs); //删除编号
        int resultAuthority = authorityMapper.deleteAllChannel(channelCodes); //批量删除权限
        if(result > 0 && resultNum > 0 && resultAuthority >= 0){
            return 1; //成功
        }else {
            return 0; //失败
        }
    }

    @Override
    public int update(Channel channel){ //修改渠道信息
        int result = channelMapper.update(channel);
        if(result > 0){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public List<Channel> queryAll(){ //查询所有渠道
        List<Channel> channelList = channelMapper.queryAll();
        if(channelList != null){
            return channelList;
        }else {
            return null;
        }
    }

    @Override
    public List<Channel> queryByKey(String key){ //关键字查询
        List<Channel> channelList = channelMapper.queryByKey(key);
        if(channelList != null){
            return channelList;
        }else {
            return null;
        }
    }

    @Override
    public List<Channel> queryByType(int channelType){ //类型查询
        List<Channel> channelList = channelMapper.queryByType(channelType);
        if(channelList != null){
            return channelList;
        }else {
            return null;
        }
    }

    @Override
    public List<Channel> queryByStatus(int channelStatus){ //状态查询
        List<Channel> channelList = channelMapper.queryByStatus(channelStatus);
        if(channelList != null){
            return channelList;
        }else {
            return null;
        }
    }

    @Override
    public List<Channel> queryByKTS(String key, int channelType, int channelStatus){ //关键字、类型、状态查询
        List<Channel> channelList = channelMapper.queryByKTS(key,channelType,channelStatus);
        if(channelList != null){
            return channelList;
        }else {
            return null;
        }
    }
}
