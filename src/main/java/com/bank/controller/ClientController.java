package com.bank.controller;

import com.bank.pojo.Client;
import com.bank.pojo.Response;
import com.bank.result.Result;
import com.bank.service.ClientService;
import com.bank.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/*
    客户
 */

@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private ResponseService responseService;

    @PostMapping("/clientLogin")
    @ResponseBody
    public Result login(@RequestBody Client client){ //客户登录验证
        Client client1 = clientService.login(client.getUsername(),client.getPassword());
        Result result;
        System.out.println(client);
        if(client1!=null){
            result = new Result(1,"登陆成功",client1);
        } else {
            result = new Result(0,"用户名或密码错误",client);
        }
        return result;
    }

    @PostMapping("/clientWithdraw")
    @ResponseBody
    public Result withdraw(@RequestBody Map<String,String> map){ //取款
        String channelCode = map.get("channelCode");
        String businessCode = map.get("businessCode");
        int re = clientService.queryByChannelAndBusiness(channelCode,businessCode);
        Response response;
        Result result;
        if(re == -1){ //渠道没有开通业务
            response = responseService.queryByCode("123456");
            result = new Result(0,"交易失败",response);
            return result;
        }else if(re == 0){ //未到营业时间
            response = responseService.queryByCode("234567");
            result = new Result(0,"交易失败",response);
            return result;
        }
        String bankCard = map.get("bankCard");
        String bankPassword = map.get("bankPassword");
        int money = Integer.parseInt(map.get("money"));
        int re2 = clientService.withdraw(bankCard,bankPassword,money);
        if(re2 == -2){ //银行卡或密码错误
            response = responseService.queryByCode("456789");
            result = new Result(0,"交易失败",response);
        }else if(re2 == -1){ //账户余额不足
            response = responseService.queryByCode("345678");
            result = new Result(0,"交易失败",response);
        }else if(re2 == 0){ //交易失败
            response = responseService.queryByCode("111111");
            result = new Result(0,"交易失败",response);
        }else{ //交易成功
            response = responseService.queryByCode("000000");
            result = new Result(1,"交易成功",response);
        }
        return result;
    }

    @PostMapping("/clientGetMoney")
    @ResponseBody
    public Result getMoney(@RequestBody Map<String,String> map){ //查询余额
        String channelCode = map.get("channelCode");
        String businessCode = map.get("businessCode");
        int re = clientService.queryByChannelAndBusiness(channelCode,businessCode);
        Response response;
        Result result;
        if(re == -1){ //渠道没有开通业务
            response = responseService.queryByCode("123456");
            result = new Result(0,"交易失败",response);
            return result;
        }else if(re == 0){ //未到营业时间
            response = responseService.queryByCode("234567");
            result = new Result(0,"交易失败",response);
            return result;
        }
        String bankCard = map.get("bankCard");
        String bankPassword = map.get("bankPassword");
        int money = clientService.getMoney(bankCard,bankPassword);
        if(money == -2){ //银行卡或密码错误
            response = responseService.queryByCode("456789");
            result = new Result(0,"交易失败",response);
        }else{ //交易成功
            response = responseService.queryByCode("000001");
            result = new Result(money,"交易成功",response);
        }
        return result;
    }

}
