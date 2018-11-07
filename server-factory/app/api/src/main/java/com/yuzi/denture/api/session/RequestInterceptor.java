package com.yuzi.denture.api.session;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RequestInterceptor extends HandlerInterceptorAdapter {

    private final String expiredUrl;

    public RequestInterceptor(String expiredUrl) {
        this.expiredUrl = expiredUrl;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        if(SessionManager.Instance().exist(request)) {
            return true;  
        }
        //token已失效，返回提示信息
        request.getRequestDispatcher(expiredUrl).forward(request, response);
        return false;
    }
}
