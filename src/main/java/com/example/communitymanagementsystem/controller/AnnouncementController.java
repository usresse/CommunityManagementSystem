package com.example.communitymanagementsystem.controller;

import com.example.communitymanagementsystem.Mapper.brean.AnnouncementBean;
import com.example.communitymanagementsystem.service.inter.AnnouncementServer;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * \* @author: Predator
 * \* Date: 2022-9-19
 * \* Time: 13:47
 * \* Description:
 * \
 */
@Controller
public class AnnouncementController {

    @Autowired
    private AnnouncementServer announcementServer;

    /**
     * @param ：
     * @return org.springframework.web.servlet.ModelAndView
     * Description:社团公告
     * @author Predator
     * @date 2022-9-2 21:31
     */
    @RequestMapping("/announcement")
    public ModelAndView announcement() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("html/activity/announcement");
        return modelAndView;
    }

    /**
     * @param
     * @title announcementSelect
     * @return org.springframework.web.servlet.ModelAndView
     * Description:
     * @author Predator
     * @date 2022-9-19 13:51
     */
    @GetMapping("/announcementSelect")
    @ResponseBody
    public PageInfo announcementSelect(Integer index) {
        return announcementServer.announcementSelect(index);
    }
}
