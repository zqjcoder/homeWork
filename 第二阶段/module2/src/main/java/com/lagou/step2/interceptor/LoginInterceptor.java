package com.lagou.step2.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 *
 * @version 1.0
 * @date 2020-03-16 18:05
 * @since JDK1.8
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object username = request.getSession().getAttribute("username");
        if (null != username && ((String) username).equalsIgnoreCase("admin")) {
            return true;
        }
        response.sendRedirect(request.getContextPath() + "/login/view");
        return false;
    }


}
