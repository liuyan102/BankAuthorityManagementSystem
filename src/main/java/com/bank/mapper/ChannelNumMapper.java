package com.bank.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/*
    渠道编码编号表数据库操作方法
 */

@Mapper
@Repository
public interface ChannelNumMapper {
    int add(int channelID); //新增编号
    int delete(int channelID); //删除编号
    int deleteAll(int[] channelIDs); //批量删除编号
    int queryMax(); //查询最大值
}
