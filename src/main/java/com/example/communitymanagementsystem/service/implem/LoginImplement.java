package com.example.communitymanagementsystem.service.implem;

import com.example.communitymanagementsystem.Mapper.brean.PersonalBean;
import com.example.communitymanagementsystem.mybatis.mappers.PersonalMapper;
import com.example.communitymanagementsystem.service.inter.LoginServer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
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
     * @param number 账号
     * @param password 密码
     * @title Validity
     * @return com.example.communitymanagementsystem.Mapper.brean.PersonalBean
     * Description:登录效验服务
     * @author Predator
     * @date 2022-10-5 17:12
     */
    @Override
    public boolean Validity(String number, String password) {
        PersonalBean personalBean = PersonalMapper.select(number);

        if (personalBean != null && personalBean.getPassword().equals(password.trim())) {
            return true;
        } else {
            return false;
        }
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
        if (PersonalMapper.select(personalBean.getNumber()) == null) {
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
