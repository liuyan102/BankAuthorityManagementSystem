package com.bank.mapper;

import com.bank.pojo.Client;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/*
    客户信息表数据库操作方法
 */

@Mapper
@Repository
public interface ClientMapper {
    Client queryByUsername(String username); //按用户名查询
    Client queryByBankCard(String bankCard); //按银行卡号查询
    int updateMoney(String bankCard,int money); //修改余额，取款
}
