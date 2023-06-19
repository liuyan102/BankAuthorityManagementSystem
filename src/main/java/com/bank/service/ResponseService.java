package com.bank.service;

import com.bank.pojo.Response;

import java.util.List;

/*
    交易响应码操作方法
 */

public interface ResponseService {
    int add(Response response); //新增响应码
    int delete(String responseCode); //删除响应码
    int deleteAll(String[] responseCodes); //批量删除响应码
    int update(Response response); //修改响应码
    Response queryByCode(String responseCode); //查询响应码
    List<Response> queryAll(); //获取所有响应码
    List<Response> queryByKT(String key,int responseType); //按关键字和类型查询
}
