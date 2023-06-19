package com.bank.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    渠道编码实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Channel {
    private String channelCode; //渠道编码
    private String channelName; //渠道名称
    private String channelDescription; //渠道描述
    private int channelType; //渠道类型
    private int channelStatus; //渠道状态
}
