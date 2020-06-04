package com.lagou.demo.controller;

import com.lagou.demo.service.IDemoService;
import com.lagou.edu.mvcframework.annotations.MyAutowired;
import com.lagou.edu.mvcframework.annotations.MyController;
import com.lagou.edu.mvcframework.annotations.MyRequestMapping;
import com.lagou.edu.mvcframework.annotations.Security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MyController
@MyRequestMapping("/demo")
@Security(value = {"admin"})
public class DemoController {

    @MyAutowired
    private IDemoService demoService;


    /**
     * URL: /demo/query?name=lisi
     * @param request
     * @param response
     * @param username
     * @return
     */
    @MyRequestMapping("/query")
    @Security(value = {"xiaoming"})
    public String query(HttpServletRequest request, HttpServletResponse response,String username) {
        return demoService.get("Welcome " + username + "!");
    }

    @MyRequestMapping("/handle01")
    @Security(value = {"lisi", "wangwu", "zhangsan"})
    public String handle01(HttpServletRequest request, HttpServletResponse response,String username) {
        return demoService.get("Welcome " + username + "!");
    }
}
