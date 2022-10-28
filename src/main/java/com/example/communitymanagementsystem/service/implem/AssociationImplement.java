package com.example.communitymanagementsystem.service.implem;

import com.example.communitymanagementsystem.Mapper.brean.*;
import com.example.communitymanagementsystem.mybatis.mappers.AssociationMapper;
import com.example.communitymanagementsystem.mybatis.mappers.PersonalMapper;
import com.example.communitymanagementsystem.service.inter.AssociationServer;
import com.example.communitymanagementsystem.service.utils.UtilsServer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * \* @author: Predator
 * \* Date: 2022-7-11
 * \* Time: 11:49
 * \* Description:
 * \
 */
@Service
public class AssociationImplement implements AssociationServer {

    @Autowired
    private AssociationMapper associationMapper;
    @Autowired
    private PersonalMapper personalMapper;


    /**
     * @param ： number
     * @return org.springframework.web.servlet.ModelAndView
     * Description:我的状态页面查询
     * @author Predator
     * @date 2022-9-2 18:46
     */
    @Override
    public ModelAndView MyClubStatus(String number) {
        ModelAndView modelAndView = new ModelAndView();
        PersonalBean personalBean = personalMapper.select(number);

        if (personalBean.getAssociationNumber() != null
                && personalBean.getAssociationNumber() != ""
                && personalBean.getAssociationNumber().length() != 0) {

            modelAndView.setViewName("html/MyClubStatus/CommunityPersonnelManagement");

        } else {
            modelAndView.setViewName("html/MyClubStatus/MyClubStatus");
        }
        return modelAndView;
    }

    /**
     * @param number
     * @return com.example.communitymanagementsystem.Mapper.brean.ApplyinformationBean
     * Description:我的状态页面的数据ApplyinformationBean申请历史信息数据获取
     * @title MyClubStatusSelectApplyinformationBean
     * @author Predator
     * @date 2022-9-12 17:36
     */
    @Override
    public AssociationBean MyClubStatusSelectAssociationBean(String number) {
        return associationMapper.MyClubStatusSelectAssociationBean(number);
    }

    /**
     * @param applyAssociation
     * @return com.example.communitymanagementsystem.Mapper.brean.AssociationBean
     * Description:我的状态页面的数据AssociationBean社团数据
     * @title MyClubStatusSelect
     * @author Predator
     * @date 2022-9-12 16:46
     */
    @Override
    public ApplyInformationBean MyClubStatusSelectApplyInformationBean(String applyAssociation) {
        return associationMapper.MyClubStatusSelectApplyinformationBean(applyAssociation);
    }

    /**
     * @param ：
     * @return com.github.pagehelper.PageInfo
     * Description:根据社团编号查询同社团的成员
     * @author Predator
     * @date 2022-9-8 11:02
     */
    @Override
    public PageInfo CommunityPersonSelect(String associationNumber, Integer index, String key, String value) {
        PageHelper.startPage(index, 6);
        List<PersonalBean> list;
        if (value == null || "".equals(value)) {
            list = personalMapper.CommunityPersonSelectNO(associationNumber);
        } else {
            if ("sex".equals(key)) {
                if ("男".endsWith(value)) {
                    value = "1";
                } else if ("女".equals(value)) {
                    value = "0";
                } else {
                    value = "null";
                }
            }

            if ("major".equals(key)) {
                value = associationMapper.majorSelect(value);
            }

            list = personalMapper.CommunityPersonSelectCondition(associationNumber, key, value);
        }
        return new PageInfo<>(list, 50);
    }

    /**
     * @param ： index
     * @return com.github.pagehelper.PageInfo
     * Description:查询社团所有信息
     * @author Predator
     * @date 2022-7-11 16:31
     */
    @Override
    public PageInfo selectAll(Integer index) {
        PageHelper.startPage(index, 6);
        List<Map<String, Object>> list = associationMapper.selectAll();
        PageInfo pageInfo = new PageInfo<>(list, 50);
        return pageInfo;
    }

    /**
     * @param ： dat
     * @return java.lang.Boolean
     * Description:生成插入社团申请信息
     * @author Predator
     * @date 2022-7-11 16:32
     */
    @Override
    public String create(String applyAssociation, String number, String applyIntroduction) {
        /**作废之前申请过有结果的申请信息*/
        associationMapper.selectupdate(number);

        if (associationMapper.create(applyAssociation, number, applyIntroduction) <= 0) {
            return "申请失败！";
        } else {
            return "申请成功！";
        }
    }

    /**
     * @param ： parseInt
     * @return java.lang.Boolean
     * Description:撤销申请信息
     * @author Predator
     * @date 2022-7-11 22:08
     */
    @Override
    public Boolean DeleteApplyInformation(Integer ApplyInformationNumber) {
        Integer result = associationMapper.DeleteApplyInformation(ApplyInformationNumber);

        if (result > 0) {
            return true;
        }
        return false;
    }

    /**
     * @param associationNumber
     * @return com.example.communitymanagementsystem.Mapper.brean.ApplyInformationBean
     * Description:社长查看申请加入本社团的申请消息数据获取
     * @title ReviewedApplyInformationBeanSelect
     * @author Predator
     * @date 2022-9-12 21:25
     */
    @Override
    public List<ApplyInformationBean> reviewedApplyInformationBeanSelect(String associationNumber) {
        return associationMapper.reviewedApplyInformationBeanSelect(associationNumber);
    }

    /**
     * @param ID
     * @param result
     * @return java.lang.Boolean
     * Description:对申请加入社团的消息审核处理（需要添加事务）
     * @title judge
     * @author Predator
     * @date 2022-9-13 20:50
     */
    @Transactional
    @Override
    public void judge(Integer ID, Boolean result) {
        //修改申请信息
        associationMapper.judge(result, ID);
        //获取申请信息
        ApplyInformationBean applyInformationBean = associationMapper.judgeSelect(ID);
        if (result) {
            //修改社团总人数
            associationMapper.judgeUpdate(applyInformationBean.getApplyAssociation());
            //修改用户的社团信息
            personalMapper.judgeUpdate(applyInformationBean.getApplyNumber(), applyInformationBean.getApplyAssociation());
        }
    }

    /**
     * @param number
     * @return java.lang.String
     * Description:
     * @title AssociationSelect
     * @author Predator
     * @date 2022-9-14 13:36
     */
    @Override
    public SchoolAgreeAssociationBean AssociationSelect(String number) {
        return associationMapper.AssociationSelect(number);
    }

    /**
     * @param schoolAgreeAssociationBean
     * @return java.lang.String
     * Description:创建社团发送申请信息
     * @title CreateAssociation
     * @author Predator
     * @date 2022-9-13 21:30
     */
    @Override
    public String CreateAssociation(SchoolAgreeAssociationBean schoolAgreeAssociationBean) {
        Integer result = associationMapper.CreateAssociation(schoolAgreeAssociationBean);
        if (result == 1) {
            return "成功！";
        }
        return "失败！";
    }

    /**
     * @param ： number
     * @return java.lang.String
     * Description:社长踢人操作
     * @author Predator
     * @date 2022-7-22 15:52
     */
    @Override
    public String KickOut(String number) {
        boolean result = associationMapper.KickOut(number);

        if (result) {
            return "踢出成功！";
        }
        return "踢出失败！";
    }

    /**
     * @param number
     * @return java.lang.String
     * Description:社长设置管理员权限
     * @title administrator
     * @author Predator
     * @date 2022-10-18 22:58
     */
    @Override
    public String administrator(String number) {
        boolean result = false;
        String major = associationMapper.selectMajor(number);

        if (major.equals("2")) {
            major = "3";
        } else if (major.equals("3")) {
            major = "2";
        }

        result = associationMapper.administrator(number, major);

        if (result) {
            return "设置成功！";
        }
        return "设置失败！";
    }

    /**
     * @param associationID
     * @return com.example.communitymanagementsystem.Mapper.brean.AssociationBean
     * Description:社团详情数据获取
     * @title AssociationApplicationSelect
     * @author Predator
     * @date 2022-9-17 16:15
     */
    @Override
    public AssociationBean AssociationApplicationSelect(String associationID) {
        return associationMapper.MyClubStatusSelectAssociationBean(associationID);
    }

    /**
     * @param ： split
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * Description:上下移动社团信息操作
     * @author Predator
     * @date 2022-7-23 12:37
     */
    @Override
    public AssociationBean MoveAssociationApplication(Integer index, String associationID) {
        return associationMapper.MoveAssociationApplication(index, associationID);
    }

    /**
     * @param ： number
     * @return com.example.communitymanagementsystem.Mapper.brean.Association
     * Description:社团信息维护
     * @author Predator
     * @date 2022-7-31 10:41
     */
    @Override
    public AssociationBean CommunityInformationMaintenanceSelect(String number) {
        return associationMapper.CommunityInformationMaintenanceSelect(number);
    }

    /**
     * @param ： associationID
     * @param ： toString
     * @return void
     * Description:社团信息图片的保存==>保存流程，一张一张图片触发保存的
     * @author Predator
     * @date 2022-7-31 21:15
     */
    @Override
    public void CommunityInformationImg(String associationID, String fileName) {
        String associationBlob = associationMapper.selectImg(associationID);
        if (associationBlob == null || "".equals(associationBlob)) {
            associationBlob = fileName;
        } else {
            String[] imgName = associationBlob.split("-");

            for (int i = 0; i < imgName.length; i++) {
                if (fileName.equals(imgName[i])) {
                    return;
                }
            }
            associationBlob = associationBlob + "-" + fileName;
        }
        associationMapper.CommunityInformationImg(associationID, associationBlob);
    }

    /**
     * @param ： associationID
     * @param ： imgName
     * @return java.lang.String
     * Description:删除图片操作
     * @author Predator
     * @date 2022-8-2 21:50
     */
    @Override
    public String CommunityInformationImgDelete(String associationID, String imgName) {
        String associationBlob = associationMapper.selectImg(associationID);
        String[] result = associationBlob.split("-");
        int index = -1;
        for (int i = 0; i < result.length; i++) {
            if (imgName.equals(result[i])) {
                index = i;
            }
        }

        if (index != -1) {
            String newAssociationBlob = UtilsServer.CombinationRemove(result, index);
            associationMapper.CommunityInformationImg(associationID, newAssociationBlob);
            return "删除成功!";
        } else {
            return "删除照片错误！";
        }

    }

    /**
     * @param ： number
     * @param ： associationID
     * @return java.lang.String
     * Description:移交社长的操作
     * @author Predator
     * @date 2022-8-1 21:28
     */
    @Override
    public String CommunityInformationHandoverUpdate(String number, String associationID) {
        Integer result = associationMapper.CommunityInformationHandoverUpdate(number, associationID);
        if (result > 0) {
            return "成功！";
        }
        return "失败！";
    }

    /**
     * @param ： associationID
     * @param ： introduce
     * @return java.lang.String
     * Description:维护社团数据中的保存数据
     * @author Predator
     * @date 2022-8-2 16:53
     */
    @Override
    public String CommunityInformationMaintenanceIntroduce(String associationID, String introduce) {
        Integer result = associationMapper.CommunityInformationMaintenanceIntroduce(associationID, introduce);
        if (result == 1) {
            return "修改成功！";
        }
        return "修改失败！";
    }

    /**
     * @param ： numebr===》查询人的ID
     * @return java.util.List<com.example.communitymanagementsystem.Mapper.brean.NoticeBean>
     * Description:社团公告查询
     * @author Predator
     * @date 2022-8-11 13:43
     */
    @Override
    public List<NoticeBean> noticeSelect(String number) {
        String noticeID = personalMapper.select(number).getAssociationNumber();
        List<NoticeBean> list = associationMapper.noticeSelect(noticeID);
        return list;
    }

    /**
     * @param ： noticeID
     * @param ： noticeName
     * @param ： notice
     * @return java.lang.String
     * Description:发布社团通知
     * @author Predator
     * @date 2022-8-12 18:09
     */
    @Override
    public String noticeAdd(NoticeBean noticeBean, String number) {
        /**获取注册时间*/
        noticeBean.setNoticeDate(UtilsServer.DateOfString(new Date()));
        /**查找社团编号*/
        noticeBean.setNoticeID(personalMapper.select(number).getAssociationNumber());

        int result = associationMapper.noticeAdd(noticeBean);

        if (result > 0) {
            return "发布成功！";
        } else {
            return "发布失败！";
        }
    }

    /**
     * @param ： id
     * @return java.lang.String
     * Description:删除社团通知
     * @author Predator
     * @date 2022-8-31 10:25
     */
    @Override
    public String noticeDelete(String id) {
        Integer result = associationMapper.noticeDelete(id);
        if ("".equals(result) || result == null) {
            return "删除失败！";
        }
        return "删除成功！";
    }

    /**
     * @param ： index
     * @param ： key
     * @param ： value
     * @return java.util.List<com.example.communitymanagementsystem.Mapper.brean.AssociationBean>
     * Description:加入社团的社团数据查询
     * @author Predator
     * @date 2022-9-8 17:19
     */
    @Override
    public PageInfo AddOrganizationSelect(Integer index, String key, String value) {
        PageHelper.startPage(index, 6);
        List<AssociationBean> list = null;
        if (value == null || "".equals(value)) {
            list = associationMapper.AddOrganizationSelectNo();
        } else {
            list = associationMapper.AddOrganizationSelectCondition(key, value);
        }
        return new PageInfo<>(list, 50);
    }

}
