package com.example.communitymanagementsystem.Mapper.brean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * \* @author: Predator
 * \* Date: 2022-7-7
 * \* Time: 15:44
 * \* Description:
 * \社团活动实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ActivityBrean {
    /**
     * 活动编号
     */
    private Integer ID;
    /**
     * 题目
     */
    private String Title;
    /**
     * 发布时间
     */
    private Date releaseDate;
    /**
     * 地点
     */
    private String Place;
    /**
     * 开始时间
     */
    private Date startDate;
    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 报名人数
     */
    private Integer numberOfPeople;
    /**
     * 人口上限
     */
    private Integer MAXPeople;
    /**
     * 状态
     */
    private String State;
    /**
     * 备注
     */
    private String Remark;
    /**
     * 主办社团ID
     */
    private String hostAssociactionID;
    /**
     * 社团名
     */
    private String associationName;
}
