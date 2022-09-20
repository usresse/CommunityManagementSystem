package com.example.communitymanagementsystem.Mapper.brean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * \* @author: Predator
 * \* Date: 2022-9-2
 * \* Time: 19:26
 * \* Description:学校专业的简单类
 * \
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class SchoolMajorBean {
    /**专业编号*/
    private Integer schoolmajorID;
    /**专业名称*/
    private String schoolmajorName;

}
