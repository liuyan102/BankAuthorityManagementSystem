package com.bank.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authority {
    private int authorityID; //权限编码
    private String channelCode; //渠道编码
    private String businessCode; //业务编码
    private String businessName; //业务编码名称
    private int openMode; //开通方式
    private String openTime; //开始时间
    private String endTime; //结束时间
}
