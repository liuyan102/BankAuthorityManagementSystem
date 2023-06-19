package com.bank.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*
    返回结果
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {
    private int code; //结果编号
    private String message; //结果信息
    private Object object; //结果附带对象
}
