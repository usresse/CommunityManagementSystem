package com.example.communitymanagementsystem.Mapper.brean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * \* @author: Predator
 * \* Date: 2022-8-8
 * \* Time: 15:06
 * \* Description:
 * \
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AnnouncementBean {
    /**
     * 公告ID
     */
    private Integer id;
    /**
     * 社团ID
     */
    private String announcementID;
    /**
     * 公告内容
     */
    private String announcement;
    /**
     * 公告时间
     */
    private String announcementDate;
}
