package com.lagou.service;

import com.lagou.vo.RpcRequest;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public String sayHello(String msg) {
        String result = "调用成功--返回：" + msg;
        System.out.println(result);
        return result;
    }
}
