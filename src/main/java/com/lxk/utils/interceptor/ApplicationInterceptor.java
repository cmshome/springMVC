package com.lxk.utils.interceptor;

import com.lxk.httpModel.SessionInfo;
import com.lxk.service.TestService;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器类。
 * 该类负责拦截用户请求
 */
public class ApplicationInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private TestService testService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("sessionInfo");

        //项目名称，根路径
        String root = request.getContextPath();
        //访问路径
        String accessPath = request.getRequestURI();
        //相对路径
        String servletPath = request.getServletPath();
        //参数
        String accessParam = request.getQueryString();
        System.out.println("项目名称，根路径" + root + ";访问路径" + accessPath + ";参数" + ";相对路径" + servletPath + accessParam);
        testService.test();
        Cookie cookie = getCookie(request);
        if (cookie != null) {
            System.out.println("cookie is " + cookie.getValue());
        }
        return true;
    }

    /**
     * 将cookie封装到Map里面
     */
    private static Cookie getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; null != cookies && i < cookies.length; i++) {
            if ("token".equals(cookies[i].getName())) {
                return cookies[i];
            }
        }
        return null;
    }
}