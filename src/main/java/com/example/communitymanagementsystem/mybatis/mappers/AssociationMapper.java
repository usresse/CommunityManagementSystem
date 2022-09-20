package com.example.communitymanagementsystem.mybatis.mappers;

import com.example.communitymanagementsystem.Mapper.brean.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * \* @author: Predator
 * \* Date: 2022-7-11
 * \* Time: 11:56
 * \* Description:
 * \
 */
@Mapper
public interface AssociationMapper {

    /**
     * @param ：
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * Description:查询全部社团
     * @author Predator
     * @date 2022-7-11 12:00
     */
    List<Map<String, Object>> selectAll();

    /**
     * @param ：
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * Description:查询指定社团
     * @author Predator
     * @date 2022-7-11 12:00
     */
    List<Map<String, Object>> select(String key, String value);


    /**
     * @param ： datum
     * @return java.lang.Integer
     * Description:创建申请操作前，作废之前已经申请过有结果的申请
     * @author Predator
     * @date 2022-7-13 16:08
     */
    Integer selectupdate(String datum);

    /**
     * @param ： applyAssociation
     * @param ： applyNumber
     * @return java.lang.Integer
     * Description:创建社团申请信息，插入数据库
     * @author Predator
     * @date 2022-7-11 16:44
     */
    Integer create(String applyAssociation, String applyNumber, String applyIntroduction);

    /**
     * @param ： number
     * @return void
     * Description:查询指定某个人的申请消息
     * @author Predator
     * @date 2022-7-11 21:31
     */
    Map<String, Object> selectInformation(String number);

    /**
     * @param applyAssociation
     * @return com.example.communitymanagementsystem.Mapper.brean.AssociationBean
     * Description:我的状态页面的数据AssociationBean社团数据
     * @title MyClubStatusSelect
     * @author Predator
     * @date 2022-9-12 16:46
     */
    ApplyInformationBean MyClubStatusSelectApplyinformationBean(String applyAssociation);


    /**
     * @param applyAssociation
     * @return com.example.communitymanagementsystem.Mapper.brean.ApplyinformationBean
     * Description:我的状态页面的数据ApplyinformationBean申请历史信息数据获取
     *              根据社团编号获取社团信息
     * @title MyClubStatusSelectApplyinformationBean
     * @author Predator
     * @date 2022-9-12 17:36
     */
    AssociationBean MyClubStatusSelectAssociationBean(String applyAssociation);

    /**
     * @param ： number
     * @return java.lang.Integer
     * Description:删除指定申请消息
     * @author Predator
     * @date 2022-7-11 22:10
     */
    Integer DeleteApplyInformation(Integer ApplyInformationNumber);


    /**
     * @param associationNumber
     * @return com.example.communitymanagementsystem.Mapper.brean.ApplyInformationBean
     * Description:社长查看申请加入本社团的申请消息数据获取
     * @title ReviewedApplyInformationBeanSelect
     * @author Predator
     * @date 2022-9-12 21:26
     */
    List<ApplyInformationBean> reviewedApplyInformationBeanSelect(String associationNumber);

    /**
     * @param result
     * @param ID
     * @return java.lang.Integer
     * Description:修改申请加入社团的信息
     * @title judge
     * @author Predator
     * @date 2022-9-13 20:28
     */
    Integer judge(Boolean result, Integer ID);

    /**
     * @param ID
     * @return java.lang.String
     * Description:根据申请加入社团的信息编号查询对象
     * @title judgeSelect
     * @author Predator
     * @date 2022-9-13 20:32
     */
    ApplyInformationBean judgeSelect(Integer ID);

    /**
     * @param applyAssociation
     * @title judgeUpdate
     * @return void
     * Description:同意加入社团人员的社团的人数添加
     * @author Predator
     * @date 2022-9-13 20:46
     */
    Integer judgeUpdate(String applyAssociation);

    /**
     * @param ： number
     * @return boolean
     * Description:社长踢人操作，根据number账号删除社团在籍
     * @author Predator
     * @date 2022-7-22 15:54
     */
    boolean KickOut(String number);

    /**
     * @param ： data
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * Description:社团详细左右移动操作
     * @author Predator
     * @date 2022-7-23 12:50
     */
    AssociationBean MoveAssociationApplication(Integer index,String associationID);

    /**
     * @param ： number
     * @return com.example.communitymanagementsystem.Mapper.brean.Association
     * Description:社团信息维护
     * @author Predator
     * @date 2022-7-31 10:42
     */
    AssociationBean CommunityInformationMaintenanceSelect(String number);

    /**
     * @param ： associationID
     * @param ： associationBlob
     * @return void
     * Description:社团信息中图片更新
     * @author Predator
     * @date 2022-7-31 21:16
     */
    void CommunityInformationImg(String associationID, String associationBlob);

    /**
     * @param ： associationID
     * @return java.lang.String
     * Description:社团信息中图片查询
     * @author Predator
     * @date 2022-7-31 21:46
     */
    String selectImg(String associationID);

    /**
     * @param ： number
     * @param ： associationID
     * @return java.lang.Integer
     * Description:移交社长操作
     * @author Predator
     * @date 2022-8-1 21:31
     */
    Integer CommunityInformationHandoverUpdate(String number, String associationID);

    /**
     * @param ： associationID
     * @param ： introduce
     * @return java.lang.Integer
     * Description:维护社团数据中的保存数据
     * @author Predator
     * @date 2022-8-2 16:55
     */
    Integer CommunityInformationMaintenanceIntroduce(String associationID, String introduce);

    /**
     * @param ： associationName
     * @return java.util.List<com.example.communitymanagementsystem.Mapper.brean.NoticeBean>
     * Description:社团公告查询
     * @author Predator
     * @date 2022-8-11 13:47
     */
    List<NoticeBean> noticeSelect(String noticeID);

    /**
     * @param ： noticeBean
     * @return int
     * Description:社团发布社团公告
     * @author Predator
     * @date 2022-8-12 18:13
     */
    int noticeAdd(NoticeBean noticeBean);

    /**
     * @param ： id
     * @return java.lang.Integer
     * Description:删除社团通知
     * @author Predator
     * @date 2022-8-31 10:26
     */
    Integer noticeDelete(String id);

    /***
     * @author Predator
     * @date 2022-9-8 13:39
     * @param ： null
     * @return
     * Description:根据社团职位查询职位编号
     */
    String majorSelect(String value);

    /**
     * @param ：
     * @return java.util.List<com.example.communitymanagementsystem.Mapper.brean.AssociationBean>
     * Description:加入社团的全部社团数据查询
     * @author Predator
     * @date 2022-9-8 17:26
     */
    List<AssociationBean> AddOrganizationSelectNo();

    /**
     * @param ： key
     * @param ： value
     * @return java.util.List<com.example.communitymanagementsystem.Mapper.brean.AssociationBean>
     * Description:加入社团的条件社团数据查询
     * @author Predator
     * @date 2022-9-8 17:26
     */
    List<AssociationBean> AddOrganizationSelectCondition(String key, String value);

    /**
     * @param number
     * @title AssociationSelect
     * @return com.example.communitymanagementsystem.Mapper.brean.SchoolAgreeAssociationBean
     * Description:根据用户账号查询申请社团信息
     * @author Predator
     * @date 2022-9-14 13:37
     */
    SchoolAgreeAssociationBean AssociationSelect(String number);

    /**
     * @param schoolAgreeAssociationBean
     * @title CreateAssociation
     * @return java.lang.Integer
     * Description:
     * @author Predator
     * @date 2022-9-13 22:18
     */
    Integer CreateAssociation(SchoolAgreeAssociationBean schoolAgreeAssociationBean);

}
