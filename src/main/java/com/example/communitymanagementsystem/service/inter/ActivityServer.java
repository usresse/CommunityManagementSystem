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

    PageInfo activity(Integer index,String[] data);

    Boolean ActivityRequest(Integer number, Map<String, Object> data);

    String updata(String number, Integer id);

    List<ActivityBrean> activityAll(String number);

    String activitysignOut(String number, Integer id);

    PageInfo ActivityHistory(Integer index,String number,String[] data);

    String ActivityHistoryDel(Integer ID);

    List<AnnouncementBean> announcement();
}
