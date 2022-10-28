package com.example.communitymanagementsystem.service.implem;

import com.example.communitymanagementsystem.Mapper.brean.ActivityBrean;
import com.example.communitymanagementsystem.mybatis.mappers.ActivityMapper;
import com.example.communitymanagementsystem.service.inter.ActivityServer;
import com.example.communitymanagementsystem.service.utils.UtilsServer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * \* @author: Predator
 * \* Date: 2022-7-14
 * \* Time: 12:59
 * \* Description:
 * \
 */
@Service
public class ActivityImplement implements ActivityServer {

    @Autowired
    private ActivityMapper activityMapper;

    /**
     * @param ： index
     * @return com.github.pagehelper.PageInfo
     * Description:
     * @author Predator
     * @date 2022-9-6 16:09
     */
    @Override
    public PageInfo<ActivityBrean> selectActivity(Integer index, String key, String value) {
        PageHelper.startPage(index, 6);
        List<ActivityBrean> list = null;
        if (value == null || "".equals(value)) {
            list = activityMapper.selectActivityNO();
        } else {
            if("hostAssociactionID".equals(key)){
                value = activityMapper.selectID(value);
            }
            list = activityMapper.selectActivityCondition(key, value);
        }
        PageInfo<ActivityBrean> pageInfo = new PageInfo<>(list, 50);
        return pageInfo;
    }

    /**
     * @param ： number
     * @param ： id
     * @return java.lang.String
     * Description:申请活动操作===>活动ID格式 id-id-id的字符串
     * @author Predator
     * @date 2022-9-2 21:26
     */
    @Override
    public String activityAdd(String number, Integer id) {
        /**社团活动查询人数满了不做后续记录*/
        boolean reuse = activityMapper.selectPeople(id);
        if (reuse) {
            return "申请活动人数已满！";
        }

        String data = activityMapper.activityStringID(number);
        int index = -1;
        /**判断是否需要添加*/
        Integer result = 0;

        /**判断没有加入过活动的情况*/
        if (data != null && data != "" && data.length() != 0) {
            String[] split = data.split("-");

            if (split.length == 6) {
                return "你申请加入活动已达上限！";
            }

            /**使用自写的工具类判断*/
            index = UtilsServer.IDExists(split, id);

            if (index == -1) {
                data += "-" + id;
                result = activityMapper.updata(number, data);
            }
        } else {
            data = id.toString();
            result = activityMapper.updata(number, data);
        }


        /**结果返回*/
        if (index != -1) {
            return "重复申请！";
        } else if (result > 0) {
            /**让活动人数加一====>1是加人*/
            activityMapper.upda(id, 1);
            return "申请成功!";
        } else {
            return "申请失败！";
        }
    }

    /**
     * @param ： number
     * @return java.util.List<com.example.communitymanagementsystem.Mapper.brean.ActivityBrean>
     * Description:查询用户是否参加活动
     * @author Predator
     * @date 2022-9-2 21:32
     */
    @Override
    public Boolean activityAll(String number) {
        String activityStringID = activityMapper.activityStringID(number);
        /**判断没加入社团时显示*/
        //activityStringID != null && activityStringID != "" && activityStringID.length() != 0
        if (activityStringID == null || activityStringID.isEmpty()) {
            return true;
        }
        return false;

    }

    /**
     * @param ： number
     * @return java.util.List<com.example.communitymanagementsystem.Mapper.brean.ActivityBrean>
     * Description:获取用户参加的几个活动数据
     * @author Predator
     * @date 2022-9-7 21:04
     */
    @Override
    public List<ActivityBrean> activityAllSelect(String number) {
        String activityStringID = activityMapper.activityStringID(number);
        List<ActivityBrean> list = new ArrayList<>();
        /**判断没加入社团时显示*/
        if (activityStringID != null && activityStringID != "" && activityStringID.length() != 0) {

            String[] data = activityStringID.split("-");
            for (int i = 0; i < data.length; i++) {
                ActivityBrean act = activityMapper.activityAll(Integer.parseInt(data[i]));
                list.add(act);
            }
            return list;
        }
        return list;
    }

    /**
     * @param ： number
     * @param ： id
     * @return java.lang.String
     * Description:做用户退出活动申请操作
     * @author Predator
     * @date 2022-9-2 21:32
     */
    @Override
    public String activitysignOut(String number, Integer id) {

        String data = activityMapper.activityStringID(number);
        String[] str = data.split("-");
        int index = UtilsServer.IDExists(str, id);

        data = UtilsServer.CombinationRemove(str, index);
        Integer result = activityMapper.updata(number, data);
        /**结果返回*/
        if (result > 0) {
            /**活动表中的人数更改 0 是减人*/
            activityMapper.upda(id, 0);
            return "退出成功!";
        } else {
            return "退出失败！";
        }
    }

    /**
     * @param ： number
     * @param ： data
     * @return java.lang.Boolean
     * Description:活动的发布请求
     * @author Predator
     * @date 2022-9-2 21:32
     */
    @Override
    public String ActivityRequest(ActivityBrean activityBrean) {

        Integer result = activityMapper.ActivityRequest(activityBrean);
        if (result > 0) {
            return "成功！";
        }
        return "失败！";
    }

    /**
     * @param index
     * @param hostAssociactionID
     * @return com.github.pagehelper.PageInfo
     * Description:社团活动历史数据获取
     * @title ActivityHistorySelect
     * @author Predator
     * @date 2022-9-15 17:37
     */
    @Override
    public PageInfo<ActivityBrean> ActivityHistorySelect(Integer index, String hostAssociactionID, String key, String value) {
        PageHelper.startPage(index, 6);
        if (value == null || "".equals(value)) {
            return new PageInfo<>(activityMapper.activityHistorySelectNo(hostAssociactionID), 50);
        } else {
            return new PageInfo<>(activityMapper.activityHistorySelectCondition(hostAssociactionID, key, value.trim()), 50);
        }
    }

    /**
     * @param ： ID
     * @return java.lang.String
     * Description:删除活动申请的历史记录
     * @author Predator
     * @date 2022-9-2 21:33
     */
    @Override
    public String ActivityHistoryDel(Integer ID) {
        int result = activityMapper.delete(ID);

        String resu = activityMapper.ActivityHistoryDel(ID);

        if ("正在进行".equals(resu)) {
            return resu;
        }

        if (result > 0) {
            return "删除成功！";
        }

        return "删除失败";
    }
}
