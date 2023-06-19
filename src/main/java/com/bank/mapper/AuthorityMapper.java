package com.bank.mapper;

import com.bank.pojo.Authority;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    权限表数据库操作方法
 */

@Mapper
@Repository
public interface AuthorityMapper {
    int add(List<Authority> authorityList); //新增权限
    int deleteChannel(String channelCode); //删除渠道对应权限信息
    int deleteAllChannel(String[] channelCodes); //批量删除渠道对应权限信息
    int deleteBusiness(String businessCode); //删除业务编码对应权限信息
    int deleteAllBusiness(String[] businessCodes); //批量删除业务编码权限信息
    List<Authority> queryByChannel(String channelCode); //按渠道查询权限
    Authority queryByChannelAndBusiness(String channelCode,String businessCode); //按渠道和业务查询权限
    List<Authority> queryBusinessAndAuthority(String channelCode);//左连接查询所有业务和权限
}
