package com.example.communitymanagementsystem.Mapper.brean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * \* @author: Predator
 * \* Date: 2022-9-13
 * \* Time: 21:19
 * \* Description:
 * \
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class SchoolAgreeAssociationBean {
    /**
     * 社团编号
     */
    String associationID;
    /**
     * 社团名
     */
    String associationName;
    /**
     * 社长账号
     */
    String associationProprieterNumber;
    /**
     * 社长
     */
    String associationProprieter;
    /**
     * 社团介绍
     */
    String associationIntroduce;

    /**
     * 申请理由
     */
    String associationReason;
    /**
     * 学校操作结果
     */
    Integer Agree;
}
