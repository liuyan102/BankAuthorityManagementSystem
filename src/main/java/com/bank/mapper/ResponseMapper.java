package com.bank.mapper;

import com.bank.pojo.Response;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    交易响应码信息表数据库操作方法
 */

@Mapper
@Repository
public interface ResponseMapper {
    int add(Response response); //新增响应码
    int delete(String responseCode); //删除响应码
    int deleteAll(String[] responseCodes); //批量删除响应码
    int update(Response response); //修改响应码
    Response queryByCode(String responseCode); //按响应码查询
    List<Response> queryAll(); //查询所有交易响应信息
    List<Response> queryByKT(String key,int responseType); //按关键字、响应类型查询
}
