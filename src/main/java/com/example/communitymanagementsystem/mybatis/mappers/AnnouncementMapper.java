package com.example.communitymanagementsystem.mybatis.mappers;

import com.example.communitymanagementsystem.Mapper.brean.AnnouncementBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * \* @author: Predator
 * \* Date: 2022-9-19
 * \* Time: 13:53
 * \* Description:
 * \
 */
@Mapper
public interface AnnouncementMapper {

    /**
     * @param
     * @title announcement
     * @return java.util.List<com.example.communitymanagementsystem.Mapper.brean.AnnouncementBean>
     * Description:
     * @author Predator
     * @date 2022-9-19 13:57
     */
    List<AnnouncementBean> announcement();
}
