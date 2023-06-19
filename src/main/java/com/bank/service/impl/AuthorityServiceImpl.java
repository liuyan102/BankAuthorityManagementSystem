package com.bank.service.impl;

import com.bank.mapper.AuthorityMapper;
import com.bank.mapper.BusinessMapper;
import com.bank.pojo.Authority;
import com.bank.pojo.Business;
import com.bank.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/*
    权限操作方法实现
 */

@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public int add(List<Authority> authorityList){ //新增权限
        int result = 0;
        String channelCode = authorityList.get(0).getChannelCode(); //获取渠道编码
        System.out.println(channelCode);
        List<Authority> authorityList1 = authorityMapper.queryByChannel(channelCode); //查询渠道权限是否存在
        if(authorityList1.size() != 0) { //渠道权限不为空
            result = authorityMapper.deleteChannel(channelCode); //删除渠道权限信息
            if(result > 0) {
            }else{
                return 0; //删除失败返回
            }
        }
        result = authorityMapper.add(authorityList); //新增渠道权限
        if(result > 0){
            return 1; //成功
        }else {
            return 0; //失败
        }
    }

    @Override
    public int delete(String channelCode){ //删除渠道权限
        List<Authority> authorityList = authorityMapper.queryByChannel(channelCode); //获取渠道权限
        int result = 0;
        if(authorityList.size() != 0) { //渠道权限不为空
            result = authorityMapper.deleteChannel(channelCode); //删除
        }else{
            result = 1;
        }
        if(result > 0){
            return 1; //成功
        }else {
            return 0; //失败
        }
    }

    @Override
    public List<Authority> queryByChannel(String channelCode){ //查询渠道权限
        List<Authority> authorityList = authorityMapper.queryByChannel(channelCode); //查询
        if(authorityList != null){
            return authorityList; //不为空
        }else{
            return null; //为空
        }
    }

    @Override
    public List<Authority> queryBusinessAndAuthority(String channelCode){ //左连接查询所有业务和权限
        List<Authority> authorityList = authorityMapper.queryBusinessAndAuthority(channelCode);
        if(authorityList != null){
            return authorityList;
        }else{
            return null;
        }
    }

    @Override
    public List<Authority> queryByChannelWithBusiness(String channelCode,String businessCode){ //查询所有业务和渠道已开通业务
        List<Business> businessList = businessMapper.queryByCode(businessCode); //获取所有业务
        List<Authority> authorityList = authorityMapper.queryByChannel(channelCode); //获取渠道开通业务
        List<Authority> authorityList1 = new ArrayList<>(); //返回数组
        for(int i=0;i<businessList.size();i++){ //将所有业务和渠道开通业务信息赋给对象并加入数组
            Authority authority = new Authority();
            authority.setBusinessCode(businessList.get(i).getBusinessCode());  //业务信息
            authority.setBusinessName(businessList.get(i).getBusinessName());
            for(int j=0;j<authorityList.size();j++){ //渠道开通业务
                if(authorityList.get(j).getBusinessCode().equals(authority.getBusinessCode())){ //如果该业务开通
                    authority.setAuthorityID(authorityList.get(j).getAuthorityID());
                    authority.setChannelCode(authorityList.get(j).getChannelCode());
                    authority.setOpenMode(authorityList.get(j).getOpenMode());
                    authority.setOpenTime(authorityList.get(j).getOpenTime());
                    authority.setEndTime(authorityList.get(j).getEndTime());
                    authorityList.remove(j);
                    break;
                }
            }
            authorityList1.add(authority); //加入数组
        }
        if(authorityList1.size() != 0){
            return authorityList1; //返回数组
        }else {
            return null;
        }
    }
}
