package com.example.communitymanagementsystem.controller;

import com.example.communitymanagementsystem.Mapper.brean.PersonalBean;
import com.example.communitymanagementsystem.service.inter.LoginServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    /**
     * @param ： number
     * @param ： password
     * @return org.springframework.web.servlet.ModelAndView
     * Description:效验登录的方法
     * @author Predator
     * @date 2022-7-3 14:07
     */
    @RequestMapping("/page")
    public ModelAndView Validity(String number, String password) {
        return loginserver.Validity(number, password);
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
