package com.example.communitymanagementsystem.service.implem;

import com.example.communitymanagementsystem.Mapper.brean.PersonalBean;
import com.example.communitymanagementsystem.mybatis.mappers.PersonalMapper;
import com.example.communitymanagementsystem.service.inter.LoginServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public PersonalBean All(String number, String password) {
        PersonalBean personalBrean = PersonalMapper.select(number);
        if(personalBrean == null){
             personalBrean = PersonalMapper.selectAll(number);
        }
        if (personalBrean != null && personalBrean.getPassword().equals(password.trim())) {
            return personalBrean;
        }
        return null;
    }

    /**
     * @param ： loginBean
     * @return java.lang.Boolean
     * Description:注册插入数据
     * @author Predator
     * @date 2022-7-3 15:34
     */
    @Override
    public Boolean insert(PersonalBean personalBean) {
        if (PersonalMapper.selectAll(personalBean.getNumber()) == null) {
            /**获取当前时间的字符串格式*/
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            int result = PersonalMapper.insert(personalBean, simpleDateFormat.format(new Date()));
            if (result != 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }
}
