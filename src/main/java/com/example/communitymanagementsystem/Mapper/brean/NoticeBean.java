package com.example.communitymanagementsystem.Mapper.brean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * \* @author: Predator
 * \* Date: 2022-8-11
 * \* Time: 13:12
 * \* Description:
 * \
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class NoticeBean {
    /**
     * 编号
     */
    private Integer ID;
    /**
     * 社团查询编号
     */
    private String noticeID;
    /**
     * 主题公告
     */
    private String noticeName;
    /**
     * 公告时间
     */
    private String noticeDate;
    /**
     * 公告内容
     */
    private String notice;
}
