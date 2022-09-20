package com.example.communitymanagementsystem.service.inter;

import com.example.communitymanagementsystem.Mapper.brean.ApplyInformationBean;
import com.example.communitymanagementsystem.Mapper.brean.AssociationBean;
import com.example.communitymanagementsystem.Mapper.brean.NoticeBean;
import com.example.communitymanagementsystem.Mapper.brean.SchoolAgreeAssociationBean;
import com.github.pagehelper.PageInfo;
import net.sf.jsqlparser.statement.create.table.Index;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * \* @author: Predator
 * \* Date: 2022-7-11
 * \* Time: 11:48
 * \* Description:
 * \
 */
public interface AssociationServer {
    ModelAndView MyClubStatus(String number);

    AssociationBean MyClubStatusSelectAssociationBean(String number);

    ApplyInformationBean MyClubStatusSelectApplyInformationBean(String applyAssociation);

    Boolean DeleteApplyInformation(Integer number);

    PageInfo selectAll(Integer index);

    String create(String applyAssociation,String number ,String applyIntroduction);

    List<ApplyInformationBean> reviewedApplyInformationBeanSelect(String associationNumber);

    void judge(Integer ID,Boolean result);

    SchoolAgreeAssociationBean AssociationSelect(String number);

    String CreateAssociation(SchoolAgreeAssociationBean schoolAgreeAssociationBean);

    String KickOut(String number);

    AssociationBean AssociationApplicationSelect(String associationID);

    AssociationBean MoveAssociationApplication(Integer index,String associationID);

    AssociationBean CommunityInformationMaintenanceSelect(String number);

    void CommunityInformationImg(String associationID, String fileName);

    String CommunityInformationHandoverUpdate(String number, String associationID);

    String CommunityInformationMaintenanceIntroduce(String associationID, String introduce);

    String CommunityInformationImgDelete(String associationID, String imgName);

    List<NoticeBean> noticeSelect(String number);

    String noticeAdd(NoticeBean noticeBean,String number);

    String noticeDelete(String id);

    PageInfo CommunityPersonSelect(String associationNumber,Integer index,String key,String value);

    PageInfo AddOrganizationSelect(Integer index, String key, String value);
}
