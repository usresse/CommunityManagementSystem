package com.example.communitymanagementsystem.mybatis.mappers;

import com.example.communitymanagementsystem.Mapper.brean.ActivityBrean;
import com.example.communitymanagementsystem.Mapper.brean.AnnouncementBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * \* @author: Predator
 * \* Date: 2022-7-14
 * \* Time: 13:16
 * \* Description:
 * \
 */
@Mapper
public interface ActivityMapper {
    /**
     * @author Predator
     * @date 2022-7-7 16:16
     * @param ：
     * @return java.util.List<com.example.communitymanagementsystem.Mapper.brean.ActivityBrean>
     * Description:查询活动管理
     */
    List<ActivityBrean> activity(String[] data);

    /**
     * @author Predator
     * @date 2022-7-14 18:22
     * @param ： number
     * @param ： data
     * @return java.lang.Integer
     * Description:社团申请活动
     */
    Integer ActivityRequest(Integer number, Map<String, Object> data);

    /**
     * @author Predator
     * @date 2022-7-16 10:15
     * @param ： number
     * @return java.lang.String
     * Description:查询personal表的活动id字符串
     */
    String select(String number);

    /**
     * @author Predator
     * @date 2022-7-16 10:16
     * @param ： number
     * @param ： data
     * @return java.lang.Integer
     * Description:对personal表的活动id字符串进行更新
     */
    Integer updata(String number, String data);

    /**
     * @author Predator
     * @date 2022-7-16 10:16
     * @param ： ID==活动ID
     * @param ： con  ==>判断加减人数的条件 1==加人   0== 减人
     * @return java.lang.Integer
     * Description:对activity活动信息中的人数加一
     */
    Integer upda(Integer ID,Integer con);

    /**
     * @author Predator
     * @date 2022-7-16 12:08
     * @param ： number
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * Description:查询用户正在参加的活动
     */
    ActivityBrean activityAll(Integer ID);

    /**
     * @author Predator
     * @date 2022-7-17 11:10
     * @param ：
     * @return boolean
     * Description:判断是否人数已满
     */
    boolean selectPeople(Integer ID);

    /**
     * @author Predator
     * @date 2022-7-17 15:28
     * @param ： number
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * Description:社团申请活动历史记录
     */
    List<Map<String, Object>> selectActivity(String number,String[] data);

    /**
     * @author Predator
     * @date 2022-7-17 15:53
     * @param ： id
     * @return int
     * Description:删除活动申请的历史记录
     */
    int delete(Integer ID);

    /**
     * @author Predator
     * @date 2022-7-20 18:52
     * @param ： number
     * @return java.lang.String
     * Description:查询社团编号，是否加入社团，加入社团后才能申请活动
     */
    String selectassociationNumber(String number);

    /**
     * @author Predator
     * @date 2022-7-21 18:27
     * @param ： id
     * @return java.lang.String
     * Description:查询活动的状态
     */
    String ActivityHistorySelect(Integer id);

    /**
     * @author Predator
     * @date 2022-8-8 15:10
     * @param ：
     * @return java.util.List<com.example.communitymanagementsystem.Mapper.brean.AnnouncementBean>
     * Description:社团公告显示
     */
    List<AnnouncementBean> announcement();
}
