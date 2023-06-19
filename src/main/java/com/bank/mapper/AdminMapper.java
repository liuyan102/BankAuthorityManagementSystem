package com.bank.mapper;

import com.bank.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/*
    管理员表数据库操作方法
 */

@Mapper
@Repository
public interface AdminMapper {
    Admin queryByUsername(String username); //使用用户名查询客户
}
