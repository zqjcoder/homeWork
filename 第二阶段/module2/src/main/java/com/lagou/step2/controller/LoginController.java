package com.lagou.step2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/view")
    public String view() throws Exception {
        return "login";
    }

    @RequestMapping("/in")
    public String in(HttpServletRequest request, HttpServletResponse response, String username, String password) throws Exception {
        if (null == username || null == password) {
            return "login";
        }
        if ("admin".equalsIgnoreCase(username) && "admin".equalsIgnoreCase(password)) {
            request.getSession().setAttribute("username", "admin");
            response.sendRedirect("/resume/queryAll");
            return "success";
        }
        return "login";
    }

}
