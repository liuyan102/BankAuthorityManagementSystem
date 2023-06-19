package com.bank.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    业务编码实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Business {
    private String businessCode; //业务编码
    private String businessName; //业务编码名称
    private String businessDescription; //业务描述
    private int businessType; //业务类型
    private int businessStatus; //业务状态
}
