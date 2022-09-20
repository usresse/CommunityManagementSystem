package com.example.communitymanagementsystem.mybatis.mappers;

import com.example.communitymanagementsystem.Mapper.brean.AssociationBean;
import com.example.communitymanagementsystem.Mapper.brean.PersonalBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * \* @author: Predator
 * \* Date: 2022-7-3
 * \* Time: 18:38
 * \* Description:
 * \
 */
@Mapper
public interface PersonalMapper {

    /**
     * @author Predator
     * @date 2022-7-4 20:40
     * @param ：
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * Description:查询所有personal，associationl连表查询   的数据
     */
    List<PersonalBean> select();

    /**
     * @author Predator
     * @date 2022-7-4 20:40
     * @param ： number
     * @return com.example.communitymanagementsystem.Mapper.brean.PersonalBean
     * Description:查询指定number，表personal，associationl连表的数据
     */
    PersonalBean select(String number);

    /**
     * @author Predator
     * @date 2022-7-4 20:40
     * @param ： number
     * @return com.example.communitymanagementsystem.Mapper.brean.PersonalBean
     * Description:查询指定number的数据
     */
    PersonalBean selectAll(String number);


    /**
     * @author Predator
     * @date 2022-7-13 22:36
     * @param ： number
     * @return java.util.List<com.example.communitymanagementsystem.Mapper.brean.PersonalBean>
     * Description:社团成员查询
     */
    List<PersonalBean> selectAssociation(String number);

    /**
     * @author Predator
     * @date 2022-9-8 11:08
     * @param ： associationNumber
     * @return java.util.List<com.example.communitymanagementsystem.Mapper.brean.PersonalBean>
     * Description:根据社团编号查询同社团的成员
     */
    List<PersonalBean> CommunityPersonSelectNO(String associationNumber);

    /**
     * @author Predator
     * @date 2022-9-8 13:30
     * @param ： associationNumber
     * @param ： key
     * @param ： value
     * @return java.util.List<com.example.communitymanagementsystem.Mapper.brean.PersonalBean>
     * Description:根据条件查询同社团的成员
     */
    List<PersonalBean> CommunityPersonSelectCondition(String associationNumber, String key, String value);

    /**
     * @author Predator
     * @date 2022-7-4 20:40
     * @param ： personalBean
     * @return int
     * Description:插入数据
     */
    int insert(PersonalBean personalBean);

    /**
     * @author Predator
     * @date 2022-7-4 21:07
     * @param ： map
     * @return int
     * Description:
     */
    int updata(PersonalBean personalBean);



    /**
     * @author Predator
     * @date 2022-7-7 21:31
     * @param ： fileName
     * @param ： number
     * @return void
     * Description:更新图片
     */
    int updataBold(String bold, Integer number);

    /**
     * @author Predator
     * @date 2022-7-13 15:10
     * @param ： number
     * @param ： applyAssociation
     * @return java.lang.Boolean
     * Description:修改personal的社团信息
     */
    Integer judgeUpdate(String number, String applyAssociation);
}
