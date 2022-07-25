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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
     * @param ：
     * @return com.github.pagehelper.PageInfo
     * Description:学校社团活动查询类
     * @author Predator
     * @date 2022-7-7 15:43
     */
    @Override
    public PageInfo activity(Integer index, String[] data) {
        PageHelper.startPage(index, 6);
        List<ActivityBrean> list = activityMapper.activity(data);

        PageInfo pageInfo = new PageInfo<>(list, 50);
        return pageInfo;
    }

    /**
     * @param ： number
     * @param ： id
     * @return boolean
     * Description:申请活动操作===>活动ID格式 id-id-id的字符串
     * @author Predator
     * @date 2022-7-16 10:10
     */
    @Override
    public String updata(String number, Integer id) {

        /**用户判断是否加入社团*/
        String associationNumber = activityMapper.selectassociationNumber(number);
        if (associationNumber.equals("")) {
            return "用户请先加入社团后再申请！";
        }

        /**社团活动查询人数满了不做后续记录*/
        boolean reuse = activityMapper.selectPeople(id);
        System.out.println(reuse);
        if (reuse) {
            return "申请活动人数已满！";
        }

        String data = activityMapper.select(number);
        int index = -1;
        /**判断是否需要添加*/
        Integer result = 0;

        /**判断没有加入过活动的情况*/
        if (data != null && data != "" && data.length() != 0) {
            String[] split = data.split("-");

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
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * Description:查询用户参加的几个活动
     * @author Predator
     * @date 2022-7-16 12:27
     */
    @Override
    public List<ActivityBrean> activityAll(String number) {
        String activityStringID = activityMapper.select(number);
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
     * @date 2022-7-16 12:50
     */
    @Override
    public String activitysignOut(String number, Integer id) {

        String data = activityMapper.select(number);
        String[] str = data.split("-");
        System.out.println(data);

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
     * @date 2022-7-14 18:16
     */
    @Override
    public Boolean ActivityRequest(Integer number, Map<String, Object> data) {
        Integer result = activityMapper.ActivityRequest(number, data);
        if (result > 0) {
            return true;
        }
        return false;
    }

    /**
     * @param ： number
     * @return com.github.pagehelper.PageInfo
     * Description:
     * @author Predator
     * @date 2022-7-17 15:23
     */
    @Override
    public PageInfo ActivityHistory(Integer index, String number,String[] data) {
        PageHelper.startPage(index, 6);

        List<Map<String, Object>> list = activityMapper.selectActivity(number,data);
        PageInfo pageInfo = new PageInfo<>(list, 50);


        return pageInfo;
    }

    /**
     * @param ： id
     * @return java.lang.String
     * Description:删除活动申请的历史记录
     * @author Predator
     * @date 2022-7-17 15:53
     */
    @Override
    public String ActivityHistoryDel(Integer ID) {
        int result = activityMapper.delete(ID);
        /**查询删除原因*/

        String resu = activityMapper.ActivityHistorySelect(ID);

        if("正在进行".equals(resu)){
            return resu;
        }

        if (result > 0) {
            return "删除成功！";
        }

        return "删除失败";
    }

}
