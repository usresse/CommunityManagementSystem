package com.example.communitymanagementsystem.mybatis.mappers;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * \* @author: Predator
 * \* Date: 2022-7-14
 * \* Time: 22:28
 * \* Description:
 * \学校端的查询
 */
@Mapper
public interface SchoolMapper {
    /**
     * @author Predator
     * @date 2022-7-15 19:22
     * @param ： number
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * Description:校方登录
     */
    Map<String, Object> login(Object number);

    /**
     * @author Predator
     * @date 2022-7-15 19:22
     * @param ：
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * Description:校方初始查询活动
     */
    List<Map<String, Object>> activity();

    /**
     * @author Predator
     * @date 2022-7-15 19:22
     * @param ： value
     * @return java.lang.Integer
     * Description:校方操作活动同意与否
     */
    Integer requset(String value,Integer ID);

    /**
     * @author Predator
     * @date 2022-7-18 18:13
     * @param ：
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * Description:学校查询申请创建社团的申请
     */
    List<Map<String, Object>> selectApplyAssociation();

    /**
     * @author Predator
     * @date 2022-7-18 18:27
     * @param ： result
     * @param ： id
     * @return java.lang.Integer
     * Description:学校查询申请创建社团的申请的结果处理
     */
    Integer schoolagreeassociation(Integer result, String associationID);
}
