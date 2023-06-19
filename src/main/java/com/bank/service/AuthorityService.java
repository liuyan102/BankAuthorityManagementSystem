package com.bank.service;

import com.bank.pojo.Authority;

import java.util.List;

/*
    权限操作方法
 */

public interface AuthorityService {
    int add(List<Authority> authorityList); //新增权限
    int delete(String channelCode); //删除渠道权限
    List<Authority> queryByChannel(String channelCode); //按渠道查询权限
    List<Authority> queryByChannelWithBusiness(String channelCode,String businessCode); //查询所有业务和该渠道开通的业务
    List<Authority> queryBusinessAndAuthority(String channelCode);//左连接查询所有业务和权限
}
