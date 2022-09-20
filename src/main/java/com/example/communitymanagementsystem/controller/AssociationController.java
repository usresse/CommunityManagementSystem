package com.example.communitymanagementsystem.controller;

import com.example.communitymanagementsystem.Mapper.brean.ApplyInformationBean;
import com.example.communitymanagementsystem.Mapper.brean.AssociationBean;
import com.example.communitymanagementsystem.Mapper.brean.NoticeBean;
import com.example.communitymanagementsystem.Mapper.brean.SchoolAgreeAssociationBean;
import com.example.communitymanagementsystem.service.inter.AssociationServer;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * Description:我的状态的页面选择
     * @param： number
     * @author Predator
     * @date 2022-9-12 16:04
     */
    @RequestMapping("/MyClubStatus/{number}")
    public ModelAndView MyClubStatus(@PathVariable("number") String number) {
        return associationServer.MyClubStatus(number);
    }

    /**
     * @param number
     * @return com.example.communitymanagementsystem.Mapper.brean.ApplyInformationBean
     * Description:我的状态页面的数据ApplyInformationBean申请历史信息数据获取
     * @title MyClubStatusSelectApplyinformationBean
     * @author Predator
     * @date 2022-9-12 17:36
     */
    @GetMapping("/MyClubStatus/MyClubStatusSelect/ApplyInformationBean")
    @ResponseBody
    public ApplyInformationBean MyClubStatusSelectApplyInformationBean(String number) {
        return associationServer.MyClubStatusSelectApplyInformationBean(number);
    }

    /**
     * @param applyAssociation
     * @return com.example.communitymanagementsystem.Mapper.brean.AssociationBean
     * Description:我的状态页面的数据AssociationBean社团数据
     * @title MyClubStatusSelect
     * @author Predator
     * @date 2022-9-12 16:46
     */
    @GetMapping("/MyClubStatus/MyClubStatusSelect/AssociationBean")
    @ResponseBody
    public AssociationBean MyClubStatusSelectAssociationBean(String applyAssociation) {
        return associationServer.MyClubStatusSelectAssociationBean(applyAssociation);
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
        Boolean result = associationServer.DeleteApplyInformation(ApplyInformationNumber);
        if (result) {
            return "撤销成功！";
        }
        return "撤销失败！";
    }

    /**
     * @param associationNumber
     * @param index
     * @param key
     * @param value
     * @return com.github.pagehelper.PageInfo
     * Description:获取一个社团的用户信息和查询某个用户
     * @title CommunityPersonSelect
     * @author Predator
     * @date 2022-9-12 16:18
     */
    @GetMapping("MyClubStatus/CommunityPersonnelManagement/CommunityPersonSelect")
    @ResponseBody
    public PageInfo CommunityPersonSelect(String associationNumber, Integer index, String key, String value) {
        return associationServer.CommunityPersonSelect(associationNumber, index, key, value);
    }


    /**
     * @param ：
     * @return org.springframework.web.servlet.ModelAndView
     * Description:加入社团的页面跳转
     * @author Predator
     * @date 2022-7-9 10:49
     */
    @RequestMapping("MyClubStatus/AddOrganization")
    public ModelAndView AddOrganization() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("html/MyClubStatus/AddOrganization");
        return modelAndView;
    }

    /***
     * @author Predator
     * @date 2022-9-8 17:13
     * @param ： null
     * @return
     * Description:加入社团的社团数据查询
     **/
    @GetMapping("MyClubStatus/AddOrganization/select")
    @ResponseBody
    public PageInfo AddOrganizationSelect(Integer index, String key, String value) {
        return associationServer.AddOrganizationSelect(index, key, value);
    }

    /**
     * @param ： associationID
     * @return org.springframework.web.servlet.ModelAndView
     * Description:社团的详情页面跳转
     * @author Predator
     * @date 2022-7-23 10:56
     */
    @RequestMapping("MyClubStatus/AssociationApplication")
    public ModelAndView AssociationApplication(@RequestParam String associationID) {
        ModelAndView modelAndView = new ModelAndView();
        if (associationID != null || "".equals(associationID)) {
            modelAndView.setViewName("html/MyClubStatus/AssociationApplication");
        }
        return modelAndView;
    }

    /**
     * @param associationID
     * @return com.example.communitymanagementsystem.Mapper.brean.AssociationBean
     * Description:社团的详情页面的数据查询
     * @title AssociationApplication
     * @author Predator
     * @date 2022-9-17 13:51
     */
    @GetMapping("MyClubStatus/AssociationApplicationSelect")
    @ResponseBody
    public AssociationBean AssociationApplicationSelect(String associationID) {
        return associationServer.AssociationApplicationSelect(associationID);
    }


    /**
     * @param ： index
     * @param ： condition
     * @return org.springframework.web.servlet.ModelAndView
     * Description:社团的详情页面的下一个或上一个的操作
     * @author Predator
     * @date 2022-7-23 10:56
     */
    @GetMapping("MyClubStatus/AssociationApplicationMove")
    @ResponseBody
    public AssociationBean MoveAssociationApplication(String associationID, Integer index) {
        return associationServer.MoveAssociationApplication(index, associationID);
    }

    /**
     * @param applyAssociation
     * @param number
     * @param applyIntroduction
     * @return java.lang.String
     * Description:申请社团的消息创建
     * @title applyInformation
     * @author Predator
     * @date 2022-9-18 10:56
     */
    @RequestMapping("MyClubStatus/AddOrganization/apply")
    @ResponseBody
    public String applyInformation(String applyAssociation, String number, String applyIntroduction) {
        return associationServer.create(applyAssociation, number, applyIntroduction);
    }

    /**
     * @param ： number
     * @return java.lang.String
     * Description:社长踢人操作
     * @author Predator
     * @date 2022-7-22 20:05
     */
    @RequestMapping("MyClubStatus/KickOut")
    @ResponseBody
    public String KickOut(@RequestParam String number) {
        return associationServer.KickOut(number);
    }


    /**
     * @param ： index
     * @param ： number
     * @return org.springframework.web.servlet.ModelAndView
     * Description:社长查看申请加入本社团的申请消息页面跳转
     * @author Predator
     * @date 2022-7-13 12:24
     */
    @RequestMapping("ToExamine")
    public ModelAndView Reviewed() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("html/ToExamine/ToExamine");
        return modelAndView;
    }

    /**
     * @param associationNumber
     * @return com.example.communitymanagementsystem.Mapper.brean.ApplyInformationBean
     * Description:社长查看申请加入本社团的申请消息数据获取
     * @title ReviewedApplyInformationBeanSelect
     * @author Predator
     * @date 2022-9-12 21:25
     */
    @GetMapping("ToExamine/ReviewedApplyInformationBeanSelect")
    @ResponseBody
    public List<ApplyInformationBean> ReviewedApplyInformationBeanSelect(String associationNumber) {
        return associationServer.reviewedApplyInformationBeanSelect(associationNumber);
    }


    /**
     * @param ： index
     * @param ： number
     * @return org.springframework.web.servlet.ModelAndView
     * Description:社长对申请加入本社团的申请消息判断操作
     * @author Predator
     * @date 2022-7-13 12:24
     */
    @RequestMapping("ToExamine/judge")
    @ResponseBody
    public void judge(Integer ID, Boolean result) {
        associationServer.judge(ID, result);
    }

    /**
     * @param ： number
     * @return org.springframework.web.servlet.ModelAndView
     * Description:申请创建社团的菜单跳转页面
     * @author Predator
     * @date 2022-7-17 21:55
     */
    @RequestMapping("ApplyAssociation")
    public ModelAndView ApplyAssociation() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("html/MyClubStatus/ApplyAssociation");
        return modelAndView;
    }

    /**
     * @param
     * @return java.lang.String
     * Description:创建社团查询是否申请过
     * @title AssociationSelect
     * @author Predator
     * @date 2022-9-14 13:03
     */
    @RequestMapping("ApplyAssociation/select")
    @ResponseBody
    public SchoolAgreeAssociationBean AssociationSelect(String number) {
        System.out.println("number = " + number);
        return associationServer.AssociationSelect(number);
    }

    /**
     * @param ： number
     * @return org.springframework.web.servlet.ModelAndView
     * Description:创建社团操作
     * @author Predator
     * @date 2022-7-17 21:55 @RequestParam
     */
    @RequestMapping("ApplyAssociation/create")
    @ResponseBody
    public String CreateAssociation(SchoolAgreeAssociationBean schoolAgreeAssociationBean) {
        return associationServer.CreateAssociation(schoolAgreeAssociationBean);
    }

    /**
     * @param ： number
     * @param ： data
     * @return org.springframework.web.servlet.ModelAndView
     * Description:社团信息维护的页面跳转
     * @author Predator
     * @date 2022-7-27 18:30
     */
    @RequestMapping("CommunityInformationMaintenance")
    public ModelAndView CommunityInformationMaintenance() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("html/MyClubStatus/CommunityInformationMaintenance");
        return modelAndView;
    }

    @GetMapping("CommunityInformationMaintenanceSelect")
    @ResponseBody
    public AssociationBean CommunityInformationMaintenanceSelect(String number) {
        return associationServer.CommunityInformationMaintenanceSelect(number);
    }

    /**
     * @param ： multipartFile
     * @param ： associationID
     * @return org.springframework.web.servlet.ModelAndView
     * Description:对社团风采图片的处理
     * @author Predator
     * @date 2022-7-31 15:50
     */
    @RequestMapping("CommunityInformationMaintenance/Img")
    @ResponseBody
    public void CommunityInformationImg(@RequestPart("file") MultipartFile multipartFile,
                                        @RequestParam("associationID") String associationID) {

        System.out.println("multipartFile = " + multipartFile + ", associationID = " + associationID);

        File file = new File("D:\\1学习文件\\毕业设计\\CommunityManagementSystem\\src\\main" +
                "\\resources\\static\\img\\association\\" + associationID);
        /*创建文件夹*/
        if (!file.exists()) {
            file.mkdirs();
        }
        /**获取文件名*/
        String fileName = multipartFile.getOriginalFilename();
        File file1 = new File(file, fileName);
        try {
            /*transferTo使用绝对路径，如果使用相对路径方法会自动生成一个父路径导致路径错误！*/
            multipartFile.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**保存数据到数据库*/
        associationServer.CommunityInformationImg(associationID, fileName);
    }

    /**
     * @param associationID
     * @param imgName
     * @return java.lang.String
     * Description:删除图片操作
     * @title CommunityInformationImgDelete
     * @author Predator
     * @date 2022-9-19 17:29
     */
    @RequestMapping("CommunityInformationMaintenance/ImgDelete")
    @ResponseBody
    public String CommunityInformationImgDelete(String associationID, String imgName) {

        File file = new File("D:\\1学习文件\\毕业设计\\CommunityManagementSystem\\src\\main" +
                "\\resources\\static\\img\\association\\" + associationID);

        File file1 = new File(file, imgName);
        file1.delete();
        return associationServer.CommunityInformationImgDelete(associationID, imgName);
    }

    /**
     * @param ： associationID
     * @param ： Introduce
     * @return org.springframework.web.servlet.ModelAndView
     * Description:维护社团数据中的保存数据
     * @author Predator
     * @date 2022-8-2 16:52
     */
    @GetMapping("CommunityInformationMaintenance/Introduce")
    @ResponseBody
    public String CommunityInformationMaintenanceIntroduce(String associationID, String associationIntroduce) {
        return associationServer.CommunityInformationMaintenanceIntroduce(associationID, associationIntroduce);
    }

    /**
     * @param ： associationID
     * @return org.springframework.web.servlet.ModelAndView
     * Description:移交社长页面跳转
     * @author Predator
     * @date 2022-7-31 22:20
     */
    @RequestMapping("CommunityInformationMaintenance/Handover")
    public ModelAndView CommunityInformationHandover() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("html/MyClubStatus/associationProprieter");
        return modelAndView;
    }

    /**
     * @param ： number
     * @param ： associationID
     * @return java.lang.String
     * Description:移交社长的操作
     * @author Predator
     * @date 2022-8-1 21:27
     */
    @RequestMapping("CommunityInformationMaintenance/Handover/update")
    @ResponseBody
    public String CommunityInformationHandoverUpdate(String number, String associationID) {
        return associationServer.CommunityInformationHandoverUpdate(number, associationID);
    }

    /**
     * @param ：
     * @return org.springframework.web.servlet.ModelAndView
     * Description:社团公告查询页面跳转
     * @author Predator
     * @date 2022-8-11 13:20
     */
    @RequestMapping("notice")
    public ModelAndView notice() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("html/MyClubStatus/notice");
        return modelAndView;
    }

    /**
     * @param ： number
     * @return java.util.List<com.example.communitymanagementsystem.Mapper.brean.NoticeBean>
     * Description:社团公告查询内容
     * @author Predator
     * @date 2022-9-3 22:15
     */
    @GetMapping("notice/select")
    @ResponseBody
    public List<NoticeBean> noticeSelect(String number) {
        return associationServer.noticeSelect(number);
    }

    /**
     * @param ：
     * @return java.lang.String
     * Description:发布通知
     * @author Predator
     * @date 2022-8-12 14:30
     */
    @GetMapping("notice/noticeAdd")
    @ResponseBody
    public String noticeAdd(NoticeBean noticeBean, String number) {
        return associationServer.noticeAdd(noticeBean, number);
    }

    /**
     * @param ： ID
     * @return java.lang.String
     * Description:删除通知
     * @author Predator
     * @date 2022-8-31 9:53
     */
    @RequestMapping("notice/noticeDelete")
    @ResponseBody
    public String noticeDelete(String ID) {
        return associationServer.noticeDelete(ID);
    }

}
