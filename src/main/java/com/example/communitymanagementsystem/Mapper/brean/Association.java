package com.example.communitymanagementsystem.Mapper.brean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * \* @author: Predator
 * \* Date: 2022-7-31
 * \* Time: 10:37
 * \* Description:
 * \
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Association {
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
     * 社团总人数
     */
    Integer associationNumber;
    /**
     * 社团风采
     */
    String associationBlob;
    /**
     * 社团介绍
     */
    String associationIntroduce;
}
