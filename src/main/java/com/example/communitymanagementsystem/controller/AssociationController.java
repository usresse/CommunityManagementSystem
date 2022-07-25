package com.example.communitymanagementsystem.controller;

import com.example.communitymanagementsystem.Mapper.brean.PersonalBean;
import com.example.communitymanagementsystem.service.inter.AssociationServer;
import com.example.communitymanagementsystem.service.inter.Server;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.sun.nio.sctp.Association;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * \* @author: Predator
 * \* Date: 2022-7-14
 * \* Time: 13:01
 * \* Description:
 * \社团页面Controller
 */
@Controller
public class AssociationController {

    @Autowired
    private AssociationServer associationServer;

    @Autowired
    private Server server;

    /**
     * @param ：
     * @return org.springframework.web.servlet.ModelAndView
     * Description:我的社团状态
     * @author Predator
     * @date 2022-7-9 10:49
     */
    @RequestMapping("/MyClubStatus/{number}")
    public ModelAndView MyClubStatus(@PathVariable("number") String number) {

        ModelAndView modelAndView = new ModelAndView();
        PersonalBean personalBean = server.select(number);

        if (personalBean == null) {
            /**测试用的输出*/
            System.out.println("personalBean对象为空");
            return modelAndView;
        }

        if (personalBean.getAssociationName() != null) {
            modelAndView.setViewName("html/MyClubStatus/CommunityPersonnelManagement");
            PageInfo pageInfo = server.PagingQuery(0,null,number);
            String data = null;
            try {
                /*new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(对象)将对象转化成json对象*/
                data = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(pageInfo);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            modelAndView.addObject("data", data);
        } else {
            modelAndView.setViewName("html/MyClubStatus/MyClubStatus");
            /**查询自己申请过社团的消息*/
            Map<String, Object> information = associationServer.select(number);

            //System.out.println("数据：");
            //for(String key :information.keySet()){
            //    System.out.println(key +" == " + information.get(key));
            //}

            modelAndView.addObject("data", information);
        }

        return modelAndView;
    }

    /**
     * @param ： number
     * @return java.lang.String
     * Description: 对于社团人员管理页面查询所有====>内部的
     * @author Predator
     * @date 2022-7-5 15:09
     */
    @RequestMapping("MyClubStatus/CommunityPersonnelManagement/{index}/{condition}/{number}")
    public ModelAndView CommunityPersonn(@PathVariable("index") Integer index,
                                         @PathVariable("condition") String condition,
                                         @PathVariable("number") String number) {

        if(condition.split(":").length == 1){
            condition = null;
        }
        PageInfo pageInfo = server.PagingQuery(index, condition, number);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("html/MyClubStatus/CommunityPersonnelManagement");
        String data = null;
        try {
            /*new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(对象)将对象转化成json对象*/
            data = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(pageInfo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("condition", condition);
        modelAndView.addObject("data", data);
        return modelAndView;
    }

    /**
     * @param ：
     * @return org.springframework.web.servlet.ModelAndView
     * Description:社团查询操作
     * @author Predator
     * @date 2022-7-9 10:49
     */
    @RequestMapping("MyClubStatus/AddOrganization/{index}/{condition}")
    public ModelAndView AddOrganization(@PathVariable("index") Integer index,
                                        @PathVariable("condition") String condition) {
        ModelAndView modelAndView = new ModelAndView();
        String[] data = condition.split(":");
        PageInfo pageInfo = null;

        if (data.length == 1) {
            pageInfo = associationServer.selectAll(index);
        } else {
            System.out.println(data[0] + "==" + data[1]);
            pageInfo = associationServer.select(index, data);
        }

        try {
            modelAndView.addObject("data",
                    new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(pageInfo));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("condition", condition);
        modelAndView.setViewName("html/MyClubStatus/AddOrganization");
        return modelAndView;
    }

    /**
     * @author Predator
     * @date 2022-7-23 10:56
     * @param ： index
     * @param ： condition
     * @return org.springframework.web.servlet.ModelAndView
     * Description:社团的详情页面
     */
    @RequestMapping("MyClubStatus/AssociationApplication")
    public ModelAndView AssociationApplication(@RequestParam String associationID) {
        ModelAndView modelAndView = new ModelAndView();
        Map<String,Object> data = associationServer.AssociationApplication(associationID);
        if(data != null){
            modelAndView.addObject("data",data);
        }
        modelAndView.setViewName("html/MyClubStatus/AssociationApplication");
        return modelAndView;
    }

    /**
     * @author Predator
     * @date 2022-7-23 10:56
     * @param ： index
     * @param ： condition
     * @return org.springframework.web.servlet.ModelAndView
     * Description:社团的详情页面的下一个或上一个的操作
     */
    @RequestMapping("MyClubStatus/AssociationApplication/move")
    public ModelAndView MoveAssociationApplication(@RequestParam String associationID) {
        ModelAndView modelAndView = new ModelAndView();
        Map<String,Object> data = associationServer.MoveAssociationApplication(associationID.split(":"));

        for(String key : data.keySet()){
            System.out.println(key +"=="+data.get(key));
        }

        if(data != null){
            modelAndView.addObject("data",data);
        }
        modelAndView.setViewName("html/MyClubStatus/AssociationApplication");
        return modelAndView;
    }

    /**
     * @author Predator
     * @date 2022-7-22 20:05
     * @param ： number
     * @return java.lang.String
     * Description:社长踢人操作
     */
    @RequestMapping("MyClubStatus/KickOut")
    @ResponseBody
    public String KickOut(@RequestParam String number) {
        return associationServer.KickOut(number);
    }

    /**
     * @param ： data ==>格式：社团编号+用户账号+个人介绍（中间以“：”间隔）
     * @return java.lang.String
     * Description:申请社团的消息创建
     * @author Predator
     * @date 2022-7-11 22:01
     */
    @RequestMapping("MyClubStatus/AddOrganization/apply/{data}")
    @ResponseBody
    public String applyInformation(@PathVariable("data") String data) {
        String[] dat = data.split(":");
        System.out.println(data);
        String aBoolean = associationServer.create(dat);
        if (aBoolean == null) {
            return "申请成功！";
        } else {
            return aBoolean;
        }
    }

    /**
     * @param ： data
     * @return java.lang.String
     * Description:申请社团的消息撤销
     * @author Predator
     * @date 2022-7-11 22:01
     */
    @RequestMapping("MyClubStatus/DeleteApplyInformation")
    @ResponseBody
    public String DeleteApplyInformation(Integer ApplyInformationNumber) {

        Boolean result = associationServer.delete(ApplyInformationNumber);
        if (result) {
            return "撤销成功！";
        }
        return "撤销失败！";
    }


    /**
     * @param ： index
     * @param ： number
     * @return org.springframework.web.servlet.ModelAndView
     * Description:社长查看申请加入本社团的申请消息
     * @author Predator
     * @date 2022-7-13 12:24
     */
    @RequestMapping("ToExamine/{index}/{number}")
    public ModelAndView Reviewed(@PathVariable("index") Integer index, @PathVariable("number") String number) {
        ModelAndView modelAndView = new ModelAndView();
        PageInfo pageInfo = associationServer.reviewed(number, index);
        try {
            modelAndView.addObject("data",
                    new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(pageInfo));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        modelAndView.setViewName("html/ToExamine/ToExamine");
        return modelAndView;
    }

    /**
     * @param ： index
     * @param ： number
     * @return org.springframework.web.servlet.ModelAndView
     * Description:判断操作
     * @author Predator
     * @date 2022-7-13 12:24
     */
    @RequestMapping("ToExamine/judge/{index}")
    @ResponseBody
    public String judge(@PathVariable("index") Integer index, String applyAssociation, String number) {
        Boolean result = associationServer.judge(index, applyAssociation, number);
        if (result) {
            return "操作成功！";
        }
        return "操作失败！";
    }

    /**
     * @param ： number
     * @return org.springframework.web.servlet.ModelAndView
     * Description:申请创建社团的菜单跳转页面
     * @author Predator
     * @date 2022-7-17 21:55
     */
    @RequestMapping("ApplyAssociation/{number}")
    public ModelAndView ApplyAssociation(@PathVariable("number") String number) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("html/MyClubStatus/ApplyAssociation");
        return modelAndView;
    }

    /**
     * @param ： number
     * @return org.springframework.web.servlet.ModelAndView
     * Description:创建社团操作
     * @author Predator
     * @date 2022-7-17 21:55
     */
    @RequestMapping("ApplyAssociation/{number}/create")
    @ResponseBody
    public String CreateAssociation(@PathVariable("number") String number,
                                    @RequestParam Map<String, Object> data) {
        return associationServer.Create(number, data);
    }
}