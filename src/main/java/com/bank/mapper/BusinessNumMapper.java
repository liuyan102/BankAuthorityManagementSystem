package com.bank.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/*
    业务编码编号表数据库操作方法
 */

@Mapper
@Repository
public interface BusinessNumMapper {
    int add(int businessID); //新增编号
    int delete(int businessID);  //删除编码
    int deleteAll(int[] businessIDs); //批量删除编号
    int queryMax(); //查找最大编号
}
