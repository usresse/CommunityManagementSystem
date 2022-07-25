package com.example.communitymanagementsystem.mybatis.mappers;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * \* @author: Predator
 * \* Date: 2022-7-10
 * \* Time: 13:57
 * \* Description:
 * \用户专业查询接口
 */
@Mapper
public interface schoolMajorMapper {
    /**
     * @author Predator
     * @date 2022-7-10 14:08
     * @param ：
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * Description：查询所有专业
     */
    List<Map<String,Object>> selectAll();
    /**
     * @author Predator
     * @date 2022-7-10 14:00
     * @param ： name
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * Description:查询
     */
    Integer select(String name);
}
