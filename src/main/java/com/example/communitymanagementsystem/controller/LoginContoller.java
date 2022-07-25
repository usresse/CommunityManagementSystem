package com.example.communitymanagementsystem.controller;

import com.example.communitymanagementsystem.Mapper.brean.PersonalBean;
import com.example.communitymanagementsystem.service.inter.LoginServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public ModelAndView page(String number, String password) {

        //System.out.println(number + "===" + password);

        ModelAndView modelAndView = new ModelAndView();
        PersonalBean personalBean = loginserver.All(number, password);

        /**跳转页面*/
        StringBuilder view = new StringBuilder();
        /**返回的信息*/
        StringBuilder str = new StringBuilder();
        /**返回头像图片地址*/
        StringBuilder url = new StringBuilder();

        if (personalBean != null) {
            if (personalBean.getMajor() != null) {
                str.append(personalBean.getMajor());
            } else {
                str.append("用户");
            }
            view.append("page");

            if (personalBean.getNickName() == "") {
                modelAndView.addObject("nickName", "null");
            } else {
                modelAndView.addObject("nickName", personalBean.getNickName());
            }
            url.append(personalBean.getBold());
            modelAndView.addObject("major", personalBean.getMajor());

            //System.out.println(personalBean.getMajor());
        } else {
            str.append("账号不存在或密码错误，请重新输入！");
            view.append("login");
        }

        modelAndView.addObject("data", str.toString());
        modelAndView.addObject("url", url.toString());
        /**返回账号*/
        modelAndView.addObject("number", number);
        modelAndView.setViewName(view.toString());
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
        Boolean result = loginserver.insert(personalBean);
        if (result) {
            return "保存成功！";
        } else {
            return "账号已存在！";
        }
    }
}
