package com.bank.service;

import com.bank.pojo.Client;

/*
    客户操作方法
 */

public interface ClientService {
    Client login(String username, String password); //登录验证
    int queryByChannelAndBusiness(String channelCode,String businessCode); //查询渠道和业务是否开通
    int withdraw(String bankCard,String bankPassword,int money); //取款
    int getMoney(String bankCard,String bankPassword); //查询余额
}
