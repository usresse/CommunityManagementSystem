package com.example.communitymanagementsystem.controller;

import com.example.communitymanagementsystem.Mapper.brean.ActivityBrean;
import com.example.communitymanagementsystem.service.inter.ActivityServer;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @param ： null
 * @author Predator
 * @date 2022-9-2 21:29
 * @return Description:
 */
@Controller
public class ActivityController {

    @Autowired
    private ActivityServer activity;

    /**
     * @param ： index
     * @param ： condition
     * @return org.springframework.web.servlet.ModelAndView
     * Description:对于社团活动管理页面跳转
     * @author Predator
     * @date 2022-9-2 21:30
     */
    @RequestMapping("/activity")
    public ModelAndView activity() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("html/activity/activity");
        return modelAndView;
    }

    /**
     * @param ：
     * @return com.github.pagehelper.PageInfo
     * Description:对于社团活动管理页面内容查询
     * @author Predator
     * @date 2022-9-6 15:55
     */
    @GetMapping("/selectActivity")
    @ResponseBody
    public PageInfo selectActivity(Integer index, String key, String value) {
        return activity.selectActivity(index, key, value);
    }

    /**
     * @param ： number
     * @param ： ID
     * @return java.lang.String
     * Description:用户对活动的申请加入操作
     * @author Predator
     * @date 2022-9-2 21:30
     */
    @GetMapping("/activityAdd")
    @ResponseBody
    public String activityAdd(String number, Integer ID) {
        return activity.activityAdd(number, ID);
    }

    /**
     * @param ： number
     * @return org.springframework.web.servlet.ModelAndView
     * Description:对参加的活动页面展示
     * @author Predator
     * @date 2022-9-2 21:30
     */
    @RequestMapping("/activityAll/{number}")
    public ModelAndView activityAll(@PathVariable("number") String number) {
        ModelAndView modelAndView = new ModelAndView();
        String url = null;
        if (activity.activityAll(number)) {
            url = "html/activity/activityAllNull";
        } else {
            url = "html/activity/activityAll";
        }
        modelAndView.setViewName(url);
        return modelAndView;
    }

    /**
     * @param ： number
     * @return java.util.List<com.example.communitymanagementsystem.Mapper.brean.ActivityBrean>
     * Description:获取参加的社团活动数据
     * @author Predator
     * @date 2022-9-7 20:51
     */
    @GetMapping("/activityAll/select")
    @ResponseBody
    public List<ActivityBrean> activityAllSelect(String number) {
        return activity.activityAllSelect(number);
    }


    /**
     * @param ： number
     * @param ： ID
     * @return java.lang.String
     * Description:用户对活动的退出操作
     * @author Predator
     * @date 2022-9-2 21:30
     */
    @PostMapping("/activityAllSignOut")
    @ResponseBody
    public String activitysignOut(String number, Integer ID) {
        return activity.activitysignOut(number, ID);
    }


    /**
     * @param ：
     * @return org.springframework.web.servlet.ModelAndView
     * Description:对于社团活动申请页面跳转
     * @author Predator
     * @date 2022-9-2 21:30
     */
    @RequestMapping("/ActivityRequest")
    public ModelAndView ActivityRequest() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("html/activity/ActivityRequest");
        return modelAndView;
    }

    /**
     * @param ： number
     * @param ： data
     * @return java.lang.String
     * Description:对于社团活动申请操作
     * @author Predator
     * @date 2022-9-2 21:30
     */
    @PostMapping("/ActivityRequest/apply")
    @ResponseBody
    public String ActivityRequest(ActivityBrean activityBrean) {
        return activity.ActivityRequest(activityBrean);
    }

    /**
     * @param ： number
     * @param ： index
     * @param ： condition
     * @return org.springframework.web.servlet.ModelAndView
     * Description:活动的社团历史显示记录
     * @author Predator
     * @date 2022-9-2 21:31
     */
    @RequestMapping("/ActivityHistory")
    public ModelAndView ActivityHistory() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("html/activity/ActivityHistory");
        return modelAndView;
    }

    /**
     * @param index
     * @param hostAssociactionID
     * @param key
     * @param value
     * @title ActivityHistorySelect
     * @return com.github.pagehelper.PageInfo
     * Description:活动的社团历史获取
     * @author Predator
     * @date 2022-9-16 11:01
     */
    @GetMapping("/ActivityHistory/select")
    @ResponseBody
    public PageInfo ActivityHistorySelect(Integer index, String hostAssociactionID,String key,String value) {
        return activity.ActivityHistorySelect(index,hostAssociactionID,key,value);
    }

    /**
     * @param ： ID
     * @return java.lang.String
     * Description:活动的社团历史显示记录删除
     * @author Predator
     * @date 2022-9-2 21:31
     */
    @GetMapping("/ActivityHistory/delete")
    @ResponseBody
    public String ActivityHistoryDel(Integer ID) {
        return activity.ActivityHistoryDel(ID);
    }

}
