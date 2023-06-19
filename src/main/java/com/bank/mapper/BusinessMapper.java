package com.bank.mapper;

import com.bank.pojo.Business;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    业务编码表数据库操作方法
 */

@Mapper
@Repository
public interface BusinessMapper {
    int add(Business business); //新增业务编码
    int delete(String businessCode); //删除业务编码
    int deleteAll(String[] businessCodes); //批量删除业务编码
    int update(Business business); //修改业务编码
    List<Business> queryAll(); //查询所有业务编码
    List<Business> queryByCode(String businessCode); //按编码查询业务
    List<Business> queryByKTS(String key,int businessType,int businessStatus); //按关键字，类型，状态查询业务
}
