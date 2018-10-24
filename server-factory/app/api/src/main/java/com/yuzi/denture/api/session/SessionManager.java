package com.yuzi.denture.api.session;

import com.yuzi.denture.domain.FactoryUser;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liyou on 2018/10/24.
 */
public class SessionManager {

    public static void cacheUser(FactoryUser user, HttpServletRequest request) {
        request.getSession().setAttribute(Cst.UserKey, user);
    }

    public static FactoryUser user(HttpServletRequest request) {
        return (FactoryUser) request.getSession().getAttribute(Cst.UserKey);
    }

}
