package com.example.communitymanagementsystem.controller;

import com.example.communitymanagementsystem.Mapper.brean.PersonalBean;
import com.example.communitymanagementsystem.service.inter.SettingServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * \* @author: Predator
 * \* Date: 2022-9-7
 * \* Time: 18:10
 * \* Description:
 * \
 */
@RequestMapping("/get")
@Controller
public class SettingController {

    @Autowired
    private SettingServer settingServer;

    /**
     * @author Predator
     * @date 2022-9-6 15:28
     * @param ：
     * @return java.lang.String
     * Description:获取用户社团名
     */
    @GetMapping("associationNumber")
    @ResponseBody
    public String associationNumber(String associationNumber) {
        return settingServer.associationNumber(associationNumber);
    }

    /**
     * @author Predator
     * @date 2022-9-6 15:28
     * @param ：
     * @return java.lang.String
     * Description:获取用户社团职位
     */
    @GetMapping("/major")
    @ResponseBody
    public String major(String major) {
        return settingServer.major(major);
    }

    /**
     * @param key==》查询的字段名
     * @param number==》查询的账号
     * @title personal
     * @return T
     * Description:
     * @author Predator
     * @date 2022-9-12 22:02
     */
    @GetMapping("/personal")
    @ResponseBody
    public String personal(String number,String key) {
        return settingServer.personal(key,number);
    }

    @GetMapping("/personal_number")
    @ResponseBody
    public PersonalBean personal(String number){
        return settingServer.personal_number(number);
    }



}
