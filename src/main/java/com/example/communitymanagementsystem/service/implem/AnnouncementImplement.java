package com.example.communitymanagementsystem.service.implem;

import com.example.communitymanagementsystem.mybatis.mappers.AnnouncementMapper;
import com.example.communitymanagementsystem.service.inter.AnnouncementServer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * \* @author: Predator
 * \* Date: 2022-9-19
 * \* Time: 13:48
 * \* Description:
 * \
 */
@Service
public class AnnouncementImplement implements AnnouncementServer {

    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public PageInfo announcementSelect(Integer index) {
        PageHelper.startPage(index, 6);
        return new PageInfo(announcementMapper.announcement(),50);
    }
}
