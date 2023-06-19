package com.bank.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    客户信息实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private int clientID; //客户编号
    private String username; //客户用户名
    private String password; //客户密码
    private String bankCard; //银行卡号
    private String bankPassword; //交易密码
    private int money; //余额
}
