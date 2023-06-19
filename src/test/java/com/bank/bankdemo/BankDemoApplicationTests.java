package com.bank.bankdemo;

import com.bank.mapper.AuthorityMapper;
import com.bank.mapper.ChannelNumMapper;
import com.bank.mapper.ClientMapper;
import com.bank.pojo.*;
import com.bank.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BankDemoApplicationTests {
    @Autowired
    AdminService adminService;
    @Autowired
    ChannelService channelService;
    @Autowired
    BusinessService businessService;
    @Autowired
    ResponseService responseService;
    @Autowired
    AuthorityService authorityService;
    @Autowired
    ChannelNumMapper channelNumMapper;
    @Autowired
    ClientService clientService;
    @Autowired
    ClientMapper clientMapper;
    @Autowired
    AuthorityMapper authorityMapper;

    @Test
    void adminTest(){
        Admin admin = adminService.login("123456","123456");
        System.out.println(admin);
    }

    @Test
    void ChannelTest(){
        Channel channel = new Channel("channel001","手机银行",
                "手机银行APP",1,1);
        //int result = channelService.delete("channel004");
        //System.out.println(result);
        //List<Channel> channelList = channelService.queryAll();
        //System.out.println(channelList);
        List<Channel> channelList1 = channelService.queryByKTS("",2,2);
        System.out.println(channelList1);
    }

    @Test
    void BusinessTest(){
        /*Business business = new Business("service001","手机银行",
                "手机银行APP",1,0);
        int result = businessService.delete(business.getBusinessCode());
        System.out.println(result);
        List<Business> businessList = businessService.queryAll();
        System.out.println(businessList);*/
        List<Business> businessList1 = businessService.queryByCode("");
        System.out.println(businessList1);
    }

    @Test
    void ResponseTest(){
        /*Response response = new Response("123456",
                "当前交易不可用或者渠道没有当前交易的调用权限，请尝试其他渠道",0);
        //int result = responseService.add(response);
        int result = responseService.update(response);
        //int result = responseService.delete(response.getResponseCode());
        System.out.println(result);
        List<Response> responseList = responseService.queryAll();
        System.out.println(responseList);*/
        List<Response> responseList1 = responseService.queryByKT("",2);
        System.out.println(responseList1);
    }

    @Test
    void AuthorityTest(){
        /*Authority authority = new Authority(0,"channel003",
                "service002","修改客户信息",1,
                "2021-08-28 00:00:00","2021-08-28 23:59:59");
        Authority authority1 = new Authority(0,"channel003",
                "service003","查询账户余额",1,
                "2021-08-28 00:00:00","2021-08-28 23:59:59");
        List<Authority> authorityList = new ArrayList<>();
        authorityList.add(authority);
        authorityList.add(authority1);
        int add = authorityService.add(authorityList);
        System.out.println(add);
        List<Authority> authorityList1 = authorityService.queryByChannelWithBusiness("channel002","");
        System.out.println(authorityList1);*/
        List<Authority> authorityList = authorityMapper.queryBusinessAndAuthority("channel001");
        System.out.println(authorityList);
    }

    @Test
    void ChannelNumTest(){
        int channelID = channelNumMapper.queryMax();
        System.out.println(channelID);
        String channelCode = "channel005";
        int channelID2 = Integer.parseInt(channelCode.substring(7));
        System.out.println(channelID2);
        int result = channelNumMapper.queryMax()+1;
        System.out.println(result);
    }

    @Test
    void ClientTest(){
        int result = clientService.queryByChannelAndBusiness("channel001","service005");
        System.out.println(result);
        Client client = clientMapper.queryByBankCard("0123456789876543210");
        System.out.println(client);
    }
}
