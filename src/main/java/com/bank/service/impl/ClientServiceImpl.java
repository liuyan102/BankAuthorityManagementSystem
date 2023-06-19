package com.bank.service.impl;

import com.bank.mapper.AuthorityMapper;
import com.bank.mapper.ClientMapper;
import com.bank.pojo.Authority;
import com.bank.pojo.Client;
import com.bank.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
    客户操作方法实现
 */

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public Client login(String username, String password){ //登录验证
        Client client = clientMapper.queryByUsername(username);
        if(client != null && password.equals(client.getPassword()))
            return client; //登陆成功
        else
            return null; //用户名或密码错误
    }

    @Override
    public int queryByChannelAndBusiness(String channelCode, String businessCode) { //查询渠道是否开通
        Authority authority = authorityMapper.queryByChannelAndBusiness(channelCode,businessCode);
        int result = 0;
        if(authority == null){
            result = -1; //渠道未开通服务
            return result;
        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //日期时间格式
            Date nowDate = new Date(); //获取当前时间
            Date openDate = simpleDateFormat.parse(authority.getOpenTime()); //获取开始时间
            Date endDate = simpleDateFormat.parse(authority.getEndTime()); //获取结束时间
            if(authority.getOpenMode()==1){ //每天开通
                String nowTime = String.format("%tT", nowDate);
                String openTime = String.format("%tT", openDate);
                String endTime = String.format("%tT", endDate);
                if(nowTime.compareTo(openTime)>0 && nowTime.compareTo(endTime)<0){ //按asc码顺序比较时间字符串
                    result = 1; // 在开通时间内
                }else{
                    result = 0; //未在开通时间内
                }
            }else{ //固定期限开通
                if(nowDate.after(openDate) && nowDate.before(endDate)){
                    result = 1; //在开通时间内
                }else {
                    result = 0; //未在开通时间内
                }
            }
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int withdraw(String bankCard,String bankPassword,int money){ //取款
        Client client = clientMapper.queryByBankCard(bankCard);
        int result;
        if(client == null || !client.getBankPassword().equals(bankPassword)){
            result = -2;
            return result; //银行卡号或密码错误
        }
        if(client.getMoney()<money){
            result = -1;
            return result; //账户余额不足
        }
        result = clientMapper.updateMoney(bankCard,client.getMoney()-money);
        if(result > 0){
            result = 1; //取款成功
        }else {
            result = 0; //取款失败
        }
        return result;
    }

    @Override
    public int getMoney(String bankCard,String bankPassword){ //查询余额
        Client client = clientMapper.queryByBankCard(bankCard);
        int result;
        if(client == null || !client.getBankPassword().equals(bankPassword)) {
            result = -2;
            return result; //银行卡号或密码错误
        }
        return client.getMoney();

    }
}
