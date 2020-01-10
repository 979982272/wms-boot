package com.tudou.system.platform.base.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/28 0028.
 */
public class AuthInteceptor implements HandlerInterceptor {

    private static Map<String, String> pathMap = null;

    static {
        pathMap = new HashMap<>();
        pathMap.put("/login", "1");
        pathMap.put("/login/valid", "1");
    }

    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        if (path.indexOf("static") > 0) {
            return true;
        }
        /*Subject subject = null;
        if (!pathMap.containsKey(path)) {
            try {
                subject = SecurityUtils.getSubject();
                if (subject == null || !subject.isAuthenticated()) {
                    response.sendRedirect("/login");
                }
            } catch (Exception e) {
                response.sendRedirect("/login");
            }
        }*/
        return true;
    }

    @Override
    public void postHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
