package com.bank.service.impl;

import com.bank.mapper.ResponseMapper;
import com.bank.pojo.Response;
import com.bank.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
    交易响应码操作
 */

@Service
@Transactional
public class ResponseServiceImpl implements ResponseService {
    @Autowired
    private ResponseMapper responseMapper;

    @Override
    public int add(Response response){ //新增响应码
        int result = responseMapper.add(response);
        if(result > 0){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public int delete(String responseCode){ //删除响应码
        int result = responseMapper.delete(responseCode);
        if(result > 0){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public int deleteAll(String[] responseCodes){ //批量删除响应码
        int result = responseMapper.deleteAll(responseCodes);
        if(result > 0){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public int update(Response response){ //修改响应码
        int result = responseMapper.update(response);
        if(result >= 0){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Response queryByCode(String responseCode){ //查询响应码
        Response response = responseMapper.queryByCode(responseCode);
        if(response != null){
            return response;
        }else {
            return null;
        }
    }

    @Override
    public List<Response> queryAll(){ //查询所有响应码
        List<Response> responseList = responseMapper.queryAll();
        if(responseList != null){
            return responseList;
        }else {
            return null;
        }
    }

    @Override
    public List<Response> queryByKT(String key,int responseType){ //关键字、状态查询
        List<Response> responseList = responseMapper.queryByKT(key,responseType);
        if(responseList != null){
            return responseList;
        }else {
            return null;
        }
    }
}
