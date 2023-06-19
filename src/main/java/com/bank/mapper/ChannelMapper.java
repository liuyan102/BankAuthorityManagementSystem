package com.bank.mapper;

import com.bank.pojo.Channel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    渠道信息表数据库操作方法
 */

@Mapper
@Repository
public interface ChannelMapper {
    int add(Channel channel); //新增渠道
    int delete(String channelCode); //删除渠道
    int deleteAll(String[] channelCodes); //批量删除渠道
    int update(Channel channel); //修改渠道
    List<Channel> queryAll(); //查询所有渠道
    List<Channel> queryByKey(String key); //按关键字模糊查询
    List<Channel> queryByType(int channelType); //按类型查询
    List<Channel> queryByStatus(int channelStatus); //按状态查询
    List<Channel> queryByKTS(String key,int channelType,int channelStatus); //按关键字、类型、状态查询

}
