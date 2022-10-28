package com.example.communitymanagementsystem.Filter;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * \* @author: Predator
 * \* Date: 2022-10-2
 * \* Time: 9:43
 * \* Description:
 * \登录拦截器拦截操作
 */
public class LoginInter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle执行了");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("login".equals(cookie.getName()) && "true".equals(cookie.getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        if ((boolean) request.getSession().getAttribute("loginResult")) {
            Cookie cookie2 = new Cookie("UserJudge", "false");
            cookie2.setMaxAge(1);
            response.addCookie(cookie2);
            /**请求转发*/
            request.getRequestDispatcher("/login").forward(request, response);
            /**重定向*/
            //response.sendRedirect(request.getContextPath() + "/login");
        }

        System.out.println("postHandle运行了");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("afterCompletion运行了");
    }
}