package com.example.communitymanagementsystem.controller;

import com.example.communitymanagementsystem.service.inter.SchoolServer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * \* @author: Predator
 * \* Date: 2022-7-14
 * \* Time: 22:27
 * \* Description:
 * \学校端的使用
 */
@Controller
public class SchoolController {
    @Autowired
    private SchoolServer schoolServer;

    /**
     * @param ：
     * @return java.lang.String
     * Description:登录页面显示
     * @author Predator
     * @date 2022-7-15 15:18
     */
    @RequestMapping("school")
    public String Login() {
        return "school/SchoolLogin";
    }

    /**
     * @param ： map
     * @return org.springframework.web.servlet.ModelAndView
     * Description:登录验证
     * @author Predator
     * @date 2022-7-15 15:18
     */
    @RequestMapping("school/login")
    public ModelAndView Login(@RequestParam Map<String, Object> map) {
        ModelAndView modelAndView = new ModelAndView();
        boolean result = schoolServer.login(map);

        if (result) {
            modelAndView.setViewName("school/main");
        } else {
            Login();
            modelAndView.setViewName("school/SchoolLogin");
            modelAndView.addObject("result", "账号或密码错误！");
        }
        return modelAndView;
    }

    /**
     * @param ：
     * @return org.springframework.web.servlet.ModelAndView
     * Description:简单页面跳转CommunityApplicationReview
     * @author Predator
     * @date 2022-7-15 15:17
     */
    @RequestMapping("school/CommunityApplicationReview")
    public ModelAndView CommunityApplicationReview() {
        ModelAndView modelAndView = new ModelAndView();
        List<Map<String, Object>> data = schoolServer.selectApplyAssociation();
        modelAndView.addObject("data", data);
        modelAndView.setViewName("school/CommunityApplicationReview");
        return modelAndView;
    }

    /**
     * @param ：
     * @return org.springframework.web.servlet.ModelAndView
     * Description:简单页面跳转CommunityApplicationReview
     * @author Predator
     * @date 2022-7-15 15:17
     */
    @RequestMapping("school/CommunityApplicationReview/{result}/{associationID}")
    @ResponseBody
    public String schoolagreeassociation(@PathVariable("result") Integer result,
                                         @PathVariable("associationID") String associationID) {
        return schoolServer.schoolagreeassociation(result, associationID);

    }

    /**
     * @param ：
     * @return org.springframework.web.servlet.ModelAndView
     * Description:简单页面跳转CommunityActivityApplicationReview
     * @author Predator
     * @date 2022-7-15 15:17
     */
    @RequestMapping("school/CommunityActivityApplicationReview")
    public ModelAndView CommunityActivityApplicationReview() {
        ModelAndView modelAndView = new ModelAndView();
        List<Map<String, Object>> data = schoolServer.activity();

        //for (Map<String, Object> map : data) {
        //    for (String key : map.keySet()) {
        //        System.out.print(key + "==" + map.get(key));
        //        System.out.print(";");
        //    }
        //    System.out.println();
        //}

        modelAndView.addObject("data", data);
        modelAndView.setViewName("school/CommunityActivityApplicationReview");
        return modelAndView;
    }

    /**
     * @param ：
     * @return org.springframework.web.servlet.ModelAndView
     * Description:学校对活动的操作
     * @author Predator
     * @date 2022-7-15 15:17
     */
    @RequestMapping("school/CommunityActivityApplicationReview/{result}/{ID}")
    @ResponseBody
    public String requset(@PathVariable("result") Integer result,
                          @PathVariable("ID") Integer ID) {
        return schoolServer.requset(result, ID);
    }

}
