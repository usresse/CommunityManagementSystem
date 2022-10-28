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
 * @author Predator
 * @date 2022-9-2 21:29
 * Description:
 */
@Controller
public class ActivityController {

    private final ActivityServer activity;

    /**构造方法注入*/
    @Autowired
    public ActivityController(ActivityServer activity) {
        this.activity = activity;
    }

    /**
     * @title activity
     * @return org.springframework.web.servlet.ModelAndView
     * Description:对于社团活动管理页面跳转
     * @author Predator
     * @date 2022-9-30 11:18
     */
    @RequestMapping("/activity")
    public ModelAndView activity() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("html/activity/activity");
        return modelAndView;
    }

    /**
     * @param index 活动编号
     * @param key 查询活动的字段名
     * @param value 查询活动的值
     * @title selectActivity
     * @return com.github.pagehelper.PageInfo
     * Description:对于社团活动管理页面内容查询
     * @author Predator
     * @date 2022-9-30 11:11
     */
    @GetMapping("/selectActivity")
    @ResponseBody
    public PageInfo<ActivityBrean> selectActivity(Integer index, String key, String value) {
        return activity.selectActivity(index, key, value);
    }

    /**
     * @param number 用户账号
     * @param ID 活动编号
     * @title activityAdd
     * @return java.lang.String
     * Description:用户对活动的申请加入操作
     * @author Predator
     * @date 2022-9-30 11:12
     */
    @GetMapping("/activityAdd")
    @ResponseBody
    public String activityAdd(String number, Integer ID) {
        return activity.activityAdd(number, ID);
    }

    /**
     * @param number 用户ID
     * @title activityAll
     * @return org.springframework.web.servlet.ModelAndView
     * Description:对参加的活动页面展示
     * @author Predator
     * @date 2022-9-30 13:52
     */
    @RequestMapping("activityAll/{number}")
    public ModelAndView activityAll(@PathVariable("number") String number) {
        ModelAndView modelAndView = new ModelAndView();
        String url;
        if (Boolean.TRUE.equals(activity.activityAll(number))) {
            url = "html/activity/activityAllNull";
        } else {
            url = "html/activity/activityAll";
        }
        modelAndView.setViewName(url);
        return modelAndView;
    }

    /**
     * @param number 用户ID
     * @title activityAllSelect
     * @return java.util.List<com.example.communitymanagementsystem.Mapper.brean.ActivityBrean>
     * Description:获取参加的社团活动数据
     * @author Predator
     * @date 2022-9-30 13:52
     */
    @GetMapping("activityAll/select")
    @ResponseBody
    public List<ActivityBrean> activityAllSelect(String number) {
        return activity.activityAllSelect(number);
    }


    /**
     * @param number 用户ID
     * @param ID 活动的编号
     * @title activitysignOut
     * @return java.lang.String
     * Description:用户对活动的退出操作
     * @author Predator
     * @date 2022-9-30 13:53
     */
    @GetMapping ("/activityAllSignOut")
    @ResponseBody
    public String activityAllSignOut(String number, Integer ID) {
        return activity.activitysignOut(number, ID);
    }


    /**
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
     * @param activityBrean 活动类
     * @title ActivityRequest
     * @return java.lang.String
     * Description:对于社团活动申请操作
     * @author Predator
     * @date 2022-9-30 11:46
     */
    @PostMapping("/ActivityRequest/apply")
    @ResponseBody
    public String ActivityRequest(ActivityBrean activityBrean) {
        return activity.ActivityRequest(activityBrean);
    }

    /**
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
     * @param index 系数
     * @param hostAssociactionID 社团ID
     * @param key 查询的字段名
     * @param value 查询的值
     * @title ActivityHistorySelect
     * @return com.github.pagehelper.PageInfo
     * Description:活动的社团历史获取
     * @author Predator
     * @date 2022-9-16 11:01
     */
    @GetMapping("/ActivityHistory/select")
    @ResponseBody
    public PageInfo<ActivityBrean> ActivityHistorySelect(Integer index, String hostAssociactionID,String key,String value) {
        return activity.ActivityHistorySelect(index,hostAssociactionID,key,value);
    }

    /**
     * @param ID 活动编号
     * @title ActivityHistoryDel
     * @return java.lang.String
     * Description: 活动的社团历史显示记录删除
     * @author Predator
     * @date 2022-9-30 11:14
     */
    @GetMapping("/ActivityHistory/delete")
    @ResponseBody
    public String ActivityHistoryDel(Integer ID) {
        return activity.ActivityHistoryDel(ID);
    }

}
