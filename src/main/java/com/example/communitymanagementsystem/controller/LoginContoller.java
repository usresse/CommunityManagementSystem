package com.example.communitymanagementsystem.controller;

import com.example.communitymanagementsystem.Mapper.brean.PersonalBean;
import com.example.communitymanagementsystem.service.inter.LoginServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * \* @author: Predator
 * \* Date: 2022-7-14
 * \* Time: 13:01
 * \* Description:
 * \登录页面，注册页面Controller
 */
@Controller
public class LoginContoller {

    @Autowired
    private LoginServer loginserver;

    /************内页面的主要显示************************************************************/
    @RequestMapping("/nei")
    public ModelAndView nei() {
        return new ModelAndView("nei");
    }

    /**********登录页面的跳转********************************************************************/
    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("/login");
    }

    /************注册页面的跳转*************************************************************/
    @RequestMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    /**
     * @param ： number
     * @param ： password
     * @return org.springframework.web.servlet.ModelAndView
     * Description:效验登录的方法
     * @author Predator   String number, String password
     * @date 2022-7-3 14:07
     */
    @RequestMapping("/page")
    public ModelAndView Validity(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String number, String password) {
        ModelAndView modelAndView = new ModelAndView("/page");
        if (loginserver.Validity(number, password)) {
            httpServletResponse.addCookie(new Cookie("number", number));
            httpServletRequest.getSession().setAttribute("loginResult",false);
        }else {
            httpServletRequest.getSession().setAttribute("loginResult",true);
        }

        return modelAndView;
    }

    /**
     * @param ： null
     * @return Description:注册
     * @author Predator
     * @date 2022-7-3 18:30
     */
    @RequestMapping("/insert")
    @ResponseBody
    public String insert(PersonalBean personalBean) {
        return loginserver.insert(personalBean);
    }
}
