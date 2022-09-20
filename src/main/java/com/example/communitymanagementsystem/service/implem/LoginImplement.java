package com.example.communitymanagementsystem.service.implem;

import com.example.communitymanagementsystem.Mapper.brean.PersonalBean;
import com.example.communitymanagementsystem.mybatis.mappers.PersonalMapper;
import com.example.communitymanagementsystem.service.inter.LoginServer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * \* @author: Predator
 * \* Date: 2022-7-2
 * \* Time: 13:21
 * \* Description:
 * \登录的操作dao
 */
@Service
public class LoginImplement implements LoginServer {
    @Autowired
    PersonalMapper PersonalMapper;

    /**
     * @param ： number
     * @param ： password
     * @return java.lang.String
     * Description:登录效验服务
     * @author Predator
     * @date 2022-7-3 15:33
     */
    @Override
    public ModelAndView Validity(String number, String password) {
        PersonalBean personalBean = PersonalMapper.selectAll(number);

        ModelAndView modelAndView = new ModelAndView();
        /**跳转页面*/
        StringBuilder view = new StringBuilder();

        if (personalBean != null && personalBean.getPassword().equals(password.trim())) {
            view.append("page");
        } else {
            modelAndView.addObject("data", "账号不存在或密码错误，请重新输入！");
            view.append("login");
        }

        try {
            modelAndView.addObject("personalBean",
                    new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(personalBean));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        modelAndView.setViewName(view.toString());

        return modelAndView;
    }


    /**
     * @param ： loginBean
     * @return java.lang.Boolean
     * Description:注册插入数据
     * @author Predator
     * @date 2022-7-3 15:34
     */
    @Override
    public String insert(PersonalBean personalBean) {
        if (PersonalMapper.selectAll(personalBean.getNumber()) == null) {
            personalBean.setDateOfRegistration(new Date());

            int result = PersonalMapper.insert(personalBean);

            if (result != 0) {
                return "创建成功！";
            } else {
                return "创建失败！";
            }
        } else {
            return "账号已经存在！";
        }

    }

}
