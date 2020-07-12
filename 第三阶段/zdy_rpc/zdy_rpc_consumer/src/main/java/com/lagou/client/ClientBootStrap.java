package com.lagou.client;

import com.lagou.service.UserService;

public class ClientBootStrap {

    public static void main(String[] args) throws InterruptedException {

        RpcConsumer rpcConsumer = new RpcConsumer();
        UserService proxy = (UserService) rpcConsumer.createProxy(UserService.class);

        while (true){
            Thread.sleep(2000);
            String result = null;
            try {
                result = proxy.sayHello("are you ok?");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(result);
        }


    }




}
