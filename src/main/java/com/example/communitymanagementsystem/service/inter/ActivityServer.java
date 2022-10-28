package com.example.communitymanagementsystem.service.inter;

import com.example.communitymanagementsystem.Mapper.brean.ActivityBrean;
import com.example.communitymanagementsystem.Mapper.brean.AnnouncementBean;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * \* @author: Predator
 * \* Date: 2022-7-14
 * \* Time: 12:59
 * \* Description:活动服务类
 * \
 */
public interface ActivityServer {

    PageInfo<ActivityBrean> selectActivity(Integer index,String key, String value);

    String activityAdd(String number, Integer id);

    String ActivityRequest(ActivityBrean activityBrean);

    Boolean activityAll(String number);

    List<ActivityBrean> activityAllSelect(String number);

    String activitysignOut(String number, Integer id);

    PageInfo<ActivityBrean> ActivityHistorySelect(Integer index, String hostAssociactionID,String key,String value);

    String ActivityHistoryDel(Integer ID);
}
