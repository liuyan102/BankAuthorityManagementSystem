package com.bank.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    管理员信息实体类
 */

@Data //为实体类提供读写属性
@AllArgsConstructor //全参构造
@NoArgsConstructor //无参构造
public class Admin {
    private int adminID; //管理员id
    private String username; //账号
    private String password; //密码
}
