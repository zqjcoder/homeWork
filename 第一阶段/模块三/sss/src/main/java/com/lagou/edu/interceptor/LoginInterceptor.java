package com.lagou.edu.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object username = request.getSession().getAttribute("username");
        if (null != username && ((String) username).equalsIgnoreCase("admin")) {
            return super.preHandle(request, response, handler);
        }
        response.sendRedirect(request.getContextPath() + "/login/view");
        return false;
    }
}
