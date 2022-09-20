package com.example.communitymanagementsystem.Mapper.brean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * \* @author: Predator
 * \* Date: 2022-9-12
 * \* Time: 17:24
 * \* Description:
 * \
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ApplyInformationBean {
    /**申请信息编码*/
    private Integer Number;
    /**申请社团编号*/
    private String applyAssociation;
    /**账号*/
    private String applyNumber;
    /**个人介绍*/
    private String applyIntroduction;
    /**状态*/
    private Integer applyT;
    /**结果*/
    private Integer applyResult;
}
