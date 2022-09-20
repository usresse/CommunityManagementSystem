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
     * @date 2022-9-7 17:18
     * @param ： hostAssociactionID
     * @return java.util.List<com.example.communitymanagementsystem.Mapper.brean.ActivityBrean>
     * Description:查询某一个社团的活动信息
     */
    List<ActivityBrean> selectActivityNO();

    /**
     * @author Predator
     * @date 2022-9-7 17:18
     * @param ： hostAssociactionID
     * @param ： key
     * @param ： value
     * @return java.util.List<com.example.communitymanagementsystem.Mapper.brean.ActivityBrean>
     * Description:根据条件查询某一个社团的活动
     */
    List<ActivityBrean> selectActivityCondition(String key,String value);

    /**
     * @author Predator
     * @date 2022-9-2 21:28
     * @param ： number
     * @param ： data
     * @return java.lang.Integer
     * Description:社团申请活动
     */
    Integer ActivityRequest(ActivityBrean activityBrean);

    /**
     * @author Predator
     * @date 2022-7-16 10:15
     * @param ： number
     * @return java.lang.String
     * Description:查询personal表的活动id字符串
     */
    String activityStringID(String number);

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
     * @date 2022-7-17 15:53
     * @param ： id
     * @return int
     * Description:删除活动申请的历史记录
     */
    int delete(Integer ID);


    /**
     * @author Predator
     * @date 2022-7-21 18:27
     * @param ： id
     * @return java.lang.String
     * Description:查询活动的状态
     */
    String ActivityHistoryDel(Integer id);

    /**
     * @param hostAssociactionID
     * @title ActivityHistorySelect
     * @return java.util.List<com.example.communitymanagementsystem.Mapper.brean.ActivityBrean>
     * Description:社团活动历史无条件数据获取
     * @author Predator
     * @date 2022-9-15 17:40
     */
    List<ActivityBrean> activityHistorySelectNo(String hostAssociactionID);

    /**
     * @param hostAssociactionID
     * @param key
     * @param value
     * @title ActivityHistorySelectCondition
     * @return java.lang.Object
     * Description:社团活动历史有条件数据获取
     * @author Predator
     * @date 2022-9-15 17:48
     */
    List<ActivityBrean> activityHistorySelectCondition(String hostAssociactionID, String key, String value);

    /**
     * @author Predator
     * @date 2022-8-8 15:10
     * @param ：
     * @return java.util.List<com.example.communitymanagementsystem.Mapper.brean.AnnouncementBean>
     * Description:社团公告显示
     */
    List<AnnouncementBean> announcement();
}
