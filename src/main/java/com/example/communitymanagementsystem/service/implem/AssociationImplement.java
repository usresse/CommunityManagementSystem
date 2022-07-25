package com.example.communitymanagementsystem.service.implem;

import com.example.communitymanagementsystem.mybatis.mappers.AssociationMapper;
import com.example.communitymanagementsystem.mybatis.mappers.PersonalMapper;
import com.example.communitymanagementsystem.service.inter.AssociationServer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * \* @author: Predator
 * \* Date: 2022-7-11
 * \* Time: 11:49
 * \* Description:
 * \
 */
@Service
public class AssociationImplement implements AssociationServer {

    @Autowired
    private AssociationMapper associationMapper;
    @Autowired
    private PersonalMapper personalMapper;


    /**
     * @param ： index
     * @return com.github.pagehelper.PageInfo
     * Description:查询社团所有信息
     * @author Predator
     * @date 2022-7-11 16:31
     */
    @Override
    public PageInfo selectAll(Integer index) {
        PageHelper.startPage(index, 6);
        List<Map<String, Object>> list = associationMapper.selectAll();
        PageInfo pageInfo = new PageInfo<>(list, 50);
        return pageInfo;
    }

    /**
     * @param ： index
     * @param ： data
     * @return com.github.pagehelper.PageInfo
     * Description:查询制定条件的社团申请的信息
     * @author Predator
     * @date 2022-7-11 16:32
     */
    @Override
    public Map<String, Object> select(String number) {
        return associationMapper.selectInformation(number);
    }

    /**
     * @param ： index
     * @param ： data
     * @return com.github.pagehelper.PageInfo
     * Description:查询制定条件的社团信息
     * @author Predator
     * @date 2022-7-11 16:32
     */
    @Override
    public PageInfo select(Integer index, String[] data) {
        PageHelper.startPage(index, 6);
        List<Map<String, Object>> list = associationMapper.select(data[0], data[1]);
        PageInfo pageInfo = new PageInfo<>(list, 50);
        return pageInfo;
    }

    /**
     * @param ： dat
     * @return java.lang.Boolean
     * Description:生成插入社团申请信息
     * @author Predator
     * @date 2022-7-11 16:32
     */
    @Override
    public String create(String[] data) {
        /**作废之前申请过有结果的申请信息*/
        associationMapper.selectupdate(data[1]);
        Map<String, Object> map = new HashMap<>();

        /**查询用户自身信息是否完善*/
        map = associationMapper.personal(data[1]);
        if (map.get("Name") == null || map.get("schoolMajor") == null) {
            return "用户信息不完善！请完善自身信息后再申请！";
        }

        /**查询是否已经申请*/
        map = associationMapper.selectApply(data[1]);
        Integer result = null;

        if (map == null || (Integer) map.get("applyT") == 0) {
            result = associationMapper.create(data[0], data[1], data[2]);
        } else {
            result = -1;
        }

        if (result <= 0) {
            return "已经申请过" + map.get("applyAssociation").toString() + "社团！";
        } else {
            return null;
        }
    }

    /**
     * @param ： parseInt
     * @return java.lang.Boolean
     * Description:撤销申请信息
     * @author Predator
     * @date 2022-7-11 22:08
     */
    @Override
    public Boolean delete(Integer number) {
        Integer result = associationMapper.delete(number);

        if (result > 0) {
            return true;
        }
        return false;
    }


    /**
     * @param ： number
     * @return com.github.pagehelper.PageInfo
     * Description:社长对申请加入社团的信息查询
     * @author Predator
     * @date 2022-7-12 21:14
     */
    @Override
    public PageInfo reviewed(String number, Integer index) {
        /**获取社长社团的编号*/
        Map<String, Object> reviewed = personalMapper.reviewed(number);
        String associationID = (String) reviewed.get("associationNumber");

        /**查询消息*/
        PageHelper.startPage(index, 6);
        List<Map<String, Object>> list = associationMapper.reviewed(associationID);

        //System.out.println("长度：" + list.size());
        //for (Map<String, Object> map : list) {
        //    for (String key : map.keySet()) {
        //        System.out.print(key + "==" + map.get(key));
        //        System.out.print(";");
        //    }
        //    System.out.println();
        //}

        PageInfo pageInfo = new PageInfo<>(list, 50);
        return pageInfo;
    }

    /**
     * @param ： index
     * @param ： applyAssociation
     * @param ： number
     * @return java.lang.Boolean
     * Description:对申请加入社团的消息审核处理（需要添加事务）
     * @author Predator
     * @date 2022-7-13 12:43
     */
    @Transactional
    @Override
    public Boolean judge(Integer index, String applyAssociation, String number) {

        associationMapper.judge(index, number);

        /**做社团人数的加一*/
        if (index == 1) {
            personalMapper.judgeUpdate(number, applyAssociation);
        }

        return true;
    }


    /**
     * @param ： number
     * @param ： data
     * @return java.lang.String
     * Description:创建社团
     * @author Predator
     * @date 2022-7-17 22:03
     */
    @Override
    public String Create(String number, Map<String, Object> data) {

        /**判断要有名字信息的输入后再创建社团*/
        if (data.get("associationProprieter") == "") {
            return "请输入个人信息中的名字后再申请！";
        }

        boolean result = associationMapper.Create(number, data);

        if (result) {
            return "申请成功！";
        }
        return "申请失败！";
    }

    /**
     * @param ： number
     * @return java.lang.String
     * Description:社长踢人操作
     * @author Predator
     * @date 2022-7-22 15:52
     */
    @Override
    public String KickOut(String number) {
        boolean result = associationMapper.KickOut(number);

        if (result) {
            return "踢出成功！";
        }
        return "踢出失败！";
    }

    /**
     * @param ： associationID
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * Description:社团详情操作
     * @author Predator
     * @date 2022-7-23 11:12
     */
    @Override
    public Map<String, Object> AssociationApplication(String associationID) {
        List<Map<String, Object>> list = associationMapper.select("associationID", associationID);
        if (list == null) {
            return null;
        }
        return list.get(0);
    }

    /**
     * @param ： split
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * Description:上下移动社团信息操作
     * @author Predator
     * @date 2022-7-23 12:37
     */
    @Override
    public Map<String, Object> MoveAssociationApplication(String[] data) {
        Map<String, Object> map = associationMapper.MoveAssociationApplication(data);
        if(map == null){
            map = associationMapper.select("associationID", data[1]).get(0);
        }
        return map;
    }
}