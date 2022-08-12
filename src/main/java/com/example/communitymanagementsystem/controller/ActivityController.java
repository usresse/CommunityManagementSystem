package com.example.communitymanagementsystem.controller;

import com.example.communitymanagementsystem.Mapper.brean.ActivityBrean;
import com.example.communitymanagementsystem.Mapper.brean.AnnouncementBean;
import com.example.communitymanagementsystem.service.inter.ActivityServer;
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
 * \* Time: 13:02
 * \* Description:
 * \活动页面Controller
 */
@Controller
public class ActivityController {

    @Autowired
    private ActivityServer activity;

    /**
     * @param ： number
     * @return java.lang.String
     * Description: 对于社团活动管理页面查询所有
     * @author Predator
     * @date 2022-7-5 15:09
     */
    @RequestMapping("/activity/{index}/{condition}")
    public ModelAndView activity(@PathVariable("index") Integer index,
                                 @PathVariable("condition") String condition) {
        ModelAndView modelAndView = new ModelAndView();
        String[] data = condition.split(":");
        PageInfo pageInfo = null;
        pageInfo = activity.activity(index, data);

        try {
            modelAndView.addObject("data",
                    new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(pageInfo));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("condition", condition);
        modelAndView.setViewName("html/activity/activity");
        return modelAndView;
    }

    /**
     * @param ： number
     * @return java.lang.String
     * Description: 用户对活动的申请加入操作
     * @author Predator
     * @date 2022-7-5 15:09
     */
    @RequestMapping("/activity/add")
    @ResponseBody
    public String activityAdd(String number, Integer ID) {
        return activity.updata(number, ID);
    }

    /**
     * @param ： number
     * @return java.lang.String
     * Description: 对参加的活动页面展示
     * @author Predator
     * @date 2022-7-5 15:09
     */
    @RequestMapping("/activityAll/{number}")
    public ModelAndView activityAll(@PathVariable("number") String number) {
        ModelAndView modelAndView = new ModelAndView();
        List<ActivityBrean> list = activity.activityAll(number);
        String url = null;

        if (list.isEmpty()) {
            url = "html/activity/activityAllNull";
        } else {
            modelAndView.addObject("data", list);
            url = "html/activity/activityAll";
        }
        modelAndView.setViewName(url);
        return modelAndView;
    }

    /**
     * @param ： number
     * @return java.lang.String
     * Description: 用户对活动的申请加入操作
     * @author Predator
     * @date 2022-7-5 15:09
     */
    @RequestMapping("/activityAll/{number}/{ID}")
    @ResponseBody
    public String activitysignOut(@PathVariable("number") String number,
                                  @PathVariable("ID") Integer ID) {
        return activity.activitysignOut(number, ID);
    }


    /**
     * @param ： number
     * @return java.lang.String
     * Description: 对于社团活动申请页面跳转
     * @author Predator
     * @date 2022-7-5 15:09
     */
    @RequestMapping("/ActivityRequest")
    public ModelAndView ActivityRequest() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("html/activity/ActivityRequest");
        return modelAndView;
    }

    /**
     * @param ： number
     * @return java.lang.String
     * Description: 对于社团活动申请操作
     * @author Predator
     * @date 2022-7-5 15:09
     */
    @RequestMapping("/ActivityRequest/{number}")
    @ResponseBody
    public String ActivityRequest(@PathVariable("number") Integer number,
                                  @RequestParam Map<String, Object> data) {
        Boolean result = activity.ActivityRequest(number, data);
        if (result) {
            return "成功！";
        }
        return "失败！";
    }

    /**
     * @param ： number
     * @return org.springframework.web.servlet.ModelAndView
     * Description:活动的社团历史显示记录
     * @author Predator
     * @date 2022-7-17 15:24
     */
    @RequestMapping("/ActivityHistory/{index}/{number}/{condition}")
    public ModelAndView ActivityHistory(@PathVariable("number") String number,
                                        @PathVariable("index") Integer index,
                                        @PathVariable("condition") String condition) {
        ModelAndView modelAndView = new ModelAndView();
        String[] data = condition.split(":");
        PageInfo pageInfo = null;

        pageInfo = activity.ActivityHistory(index, number, data);
        try {
            modelAndView.addObject("data",
                    new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(pageInfo));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("condition", condition);
        modelAndView.setViewName("html/activity/ActivityHistory");
        return modelAndView;
    }

    /**
     * @param ： number
     * @return org.springframework.web.servlet.ModelAndView
     * Description:活动的社团历史显示记录删除
     * @author Predator
     * @date 2022-7-17 15:24
     */
    @RequestMapping("/ActivityHistory/{ID}")
    @ResponseBody
    public String ActivityHistoryDel(@PathVariable("ID") Integer ID) {
        return activity.ActivityHistoryDel(ID);
    }


    /**
     * @param ：
     * @return org.springframework.web.servlet.ModelAndView
     * Description:社团公告
     * @author Predator
     * @date 2022-8-8 12:24
     */
    @RequestMapping("/announcement")
    public ModelAndView announcement() {
        ModelAndView modelAndView = new ModelAndView();
        List<AnnouncementBean> list = activity.announcement();
        modelAndView.addObject("data",list);
        modelAndView.setViewName("html/activity/announcement");
        return modelAndView;
    }

}
