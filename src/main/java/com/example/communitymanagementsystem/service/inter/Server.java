package com.example.communitymanagementsystem.service.inter;

import com.example.communitymanagementsystem.Mapper.brean.PersonalBean;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * \* @author: Predator
 * \* Date: 2022-7-4
 * \* Time: 9:27
 * \* Description:
 * \
 */
public interface Server {
    /**
     * @author Predator
     * @date 2022-7-4 9:29
     * @param ： number
     * @return PersonalBean
     * Description: 查询一掉制定number的数据
     */
    PersonalBean select(String number);

    /**
     * @author Predator
     * @date 2022-7-5 15:12
     * @param ： map
     * @return java.lang.Boolean
     * Description:查询成员分页查询每页6人
     */
    PageInfo PagingQuery(Integer index,String condition,String number);


    /**
     * @author Predator
     * @date 2022-7-7 21:28
     * @param ： fileName
     * @param ： number
     * @return void
     * Description:插入图片
     */
    String updataBold(String fileName, String number);

    /**
     * @author Predator
     * @date 2022-7-5 19:56
     * @param ： map
     * @return java.lang.Boolean
     * Description:修改数据
     */
    Boolean updata(Map<String, Object> map);

    /**
     * @author Predator
     * @date 2022-7-14 13:15
     * @param ：
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * Description:
     */
    List<Map<String, Object>> selectschoolMajor();
}
