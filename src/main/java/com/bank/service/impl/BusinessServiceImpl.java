package com.bank.service.impl;

import com.bank.mapper.AuthorityMapper;
import com.bank.mapper.BusinessMapper;
import com.bank.mapper.BusinessNumMapper;
import com.bank.pojo.Business;
import com.bank.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
    业务编码操作方法
 */

@Service
@Transactional
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private BusinessNumMapper businessNumMapper;
    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public String getCode(){ //获取编码
        int maxBusinessNum = businessNumMapper.queryMax(); //找到编号最大值
        String businessCode = String.format("service%03d", maxBusinessNum+1); //最大值+1，不足三位补0
        return businessCode;
    }

    @Override
    public int add(Business business){ //新增业务编码
        int result = businessMapper.add(business); //业务编码添加
        int resultNum = businessNumMapper.add(businessNumMapper.queryMax()+1); //业务编号添加
        if(result > 0 && resultNum > 0){
            return 1; //成功
        }else {
            return 0; //失败
        }
    }

    @Override
    public int delete(String businessCode){ //删除业务编码
        int result = businessMapper.delete(businessCode); //删除业务编码表中业务
        int businessID = Integer.parseInt(businessCode.substring(7)); //获取业务编码的编号
        int resultNum = businessNumMapper.delete(businessID); //删除业务编码编号
        int resultAuthority = authorityMapper.deleteBusiness(businessCode); //删除权限表中业务编码
        if(result > 0 && resultNum > 0 && resultAuthority >= 0){
            return 1; //成功
        }else {
            return 0; //失败
        }
    }

    @Override
    public int deleteAll(String[] businessCodes){ //批量删除
        int result = businessMapper.deleteAll(businessCodes); //批量删除业务编码
        int []businessIDs = new int[businessCodes.length];
        for(int i=0;i<businessCodes.length;i++){ //获取所有业务编码的编号
            businessIDs[i] = Integer.parseInt(businessCodes[i].substring(7));
        }
        int resultNum = businessNumMapper.deleteAll(businessIDs); //批量删除业务编码编号
        int resultAuthority = authorityMapper.deleteAllBusiness(businessCodes); //批量删除权限表中业务编码
        if(result > 0 && resultNum > 0 && resultAuthority >= 0){
            return 1; //成功
        }else {
            return 0; //失败
        }
    }

    @Override
    public int update(Business business){ //修改业务编码
        int result = businessMapper.update(business); //修改业务编码
        if(result > 0){
            return 1; //成功
        }else {
            return 0; //失败
        }
    }

    @Override
    public List<Business> queryAll(){ //获取所有业务编码
        List<Business> businessList = businessMapper.queryAll();
        if(businessList != null){
            return businessList;
        }else {
            return null;
        }
    }

    @Override
    public List<Business> queryByCode(String businessCode){ //查询业务编码
        List<Business> businessList = businessMapper.queryByCode(businessCode);
        if(businessList != null){
            return businessList;
        }else {
            return null;
        }
    }

    @Override
    public List<Business> queryByKTS(String key,int businessType,int businessStatus){ //按关键字、类型、状态查询
        List<Business> businessList = businessMapper.queryByKTS(key,businessType,businessStatus);
        if(businessList != null){
            return businessList;
        }else {
            return null;
        }
    }
}
