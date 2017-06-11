package com.dszshen.wxsdk.intercepters;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 * Created by zhangbin on 2016/7/3 0003.
 */
public class UserIntercepter implements HandlerInterceptor {

    private static final String[] INTERCEPTER_IGNORED = new String[]{"/user/useractive",
            "/user/alipay/callback"};

    private static final String[] AUTH_IGNORED = new String[]{"/user/smscode" //
            , "/user/account/reg" //

    };


    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
