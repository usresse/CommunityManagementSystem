package com.example.communitymanagementsystem.mybatis.mappers;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * \* @author: Predator
 * \* Date: 2022-7-11
 * \* Time: 11:56
 * \* Description:
 * \
 */
@Mapper
public interface AssociationMapper {

    /**
     * @param ：
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * Description:查询全部社团
     * @author Predator
     * @date 2022-7-11 12:00
     */
    List<Map<String, Object>> selectAll();

    /**
     * @param ：
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * Description:查询指定社团
     * @author Predator
     * @date 2022-7-11 12:00
     */
    List<Map<String, Object>> select(String key, String value);


    /**
     * @author Predator
     * @date 2022-7-13 16:08
     * @param ： datum
     * @return java.lang.Integer
     * Description:创建申请操作前，作废之前已经申请过有结果的申请
     */
    Integer selectupdate(String datum);

    /**
     * @author Predator
     * @date 2022-7-11 16:44
     * @param ： applyAssociation
     * @param ： applyNumber
     * @return java.lang.Integer
     * Description:创建社团申请信息，插入数据库
     */
    Integer create(String applyAssociation, String applyNumber,String applyIntroduction);

    /**
     * @author Predator
     * @date 2022-7-11 16:45
     * @param ： applyNumber
     * @param ： number
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * Description:创建社团申请信息   的重复申请查询
     */
    Map<String, Object> selectApply(String applyNumber);

    /**
     * @author Predator
     * @date 2022-7-11 21:31
     * @param ： number
     * @return void
     * Description:查询指定某个人的申请消息
     */
    Map<String, Object> selectInformation(String number);

    /**
     * @author Predator
     * @date 2022-7-11 22:10
     * @param ： number
     * @return java.lang.Integer
     * Description:删除指定申请消息
     */
    Integer delete(Integer number);

    /**
     * @author Predator
     * @date 2022-7-12 21:23
     * @param ： associationID
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * Description:社团查询申请本社团的申请消息
     */
    List<Map<String, Object>> reviewed(String associationID);

    /**
     * @author Predator
     * @date 2022-7-13 12:51
     * @param ： index
     * @param ： number
     * @return java.lang.Boolean
     * Description:社长对申请加入社团的消息进行操作
     */
    Integer judge(Integer index, String number);

    /**
     * @author Predator
     * @date 2022-7-17 22:04
     * @param ： number
     * @param ： data
     * @return boolean
     * Description:创建社团
     */
    boolean Create(String number, Map<String, Object> data);

    /**
     * @author Predator
     * @date 2022-7-20 15:43
     * @param ： datum
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * Description:查询申请人自身信息是否完整
     */
    Map<String, Object> personal(String number);

    /**
     * @author Predator
     * @date 2022-7-22 15:54
     * @param ： number
     * @return boolean
     * Description:社长踢人操作，根据number账号删除社团在籍
     */
    boolean KickOut(String number);

    /**
     * @author Predator
     * @date 2022-7-23 12:50
     * @param ： data
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * Description:社团详细左右移动操作
     */
    Map<String, Object> MoveAssociationApplication(String[] data);
}
