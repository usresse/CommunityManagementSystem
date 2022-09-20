package com.example.communitymanagementsystem.service.implem;

import com.example.communitymanagementsystem.Mapper.brean.SchoolMajorBean;
import com.example.communitymanagementsystem.mybatis.mappers.SchoolMapper;
import com.example.communitymanagementsystem.service.inter.SchoolServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * \* @author: Predator
 * \* Date: 2022-7-14
 * \* Time: 22:30
 * \* Description:
 * \学校服务类的实体类
 */
@Service
public class SchoolImplement implements SchoolServer {

    @Autowired
    private SchoolMapper schoolMapper;

    /**
     * @author Predator
     * @date 2022-9-2 19:21
     * @param ： map
     * @return java.lang.Boolean
     * Description:
     */
    @Override
    public Boolean login(Map<String, Object> map) {
        Map<String, Object> result = schoolMapper.login(map.get("number"));

        if (result != null && result.get("Password").equals(map.get("password"))) {
            return true;
        }
        return false;
    }

    /**
     * @author Predator
     * @date 2022-9-2 19:21
     * @param ：
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * Description:设置页面中查询专业
     */
    @Override
    public List<SchoolMajorBean> selectschoolMajor() {
        return schoolMapper.selectAll();
    }

    /**
     * @param ： null
     * @return Description:
     * @author Predator
     * @date 2022-7-15 17:02
     */

    @Override
    public List<Map<String, Object>> activity() {
        return schoolMapper.activity();
    }

    /**
     * @param ： result
     * @param ： ID
     * @return java.lang.String
     * Description:学校操作活动的结果操作
     * @author Predator
     * @date 2022-7-15 19:37
     */
    @Override
    public String requset(Integer result, Integer ID) {
        String value;
        if (result == 0) {
            value = "正在进行";
        } else {
            value = "拒绝";
        }
        Integer ind = schoolMapper.requset(value, ID);

        if (ind > 0) {
            return "操作成功！";
        } else {
            return "操作失败！";
        }
    }


    /**
     * @param ：
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * Description:学校查询申请创建社团的申请
     * @author Predator
     * @date 2022-7-18 18:12
     */
    @Override
    public List<Map<String, Object>> selectApplyAssociation() {
        return schoolMapper.selectApplyAssociation();
    }

    /**
     * @param ： result
     * @param ： id
     * @return java.lang.String
     * Description:学校查询申请创建社团的申请的结果处理
     * @author Predator
     * @date 2022-7-18 18:25
     */
    @Override
    public String schoolagreeassociation(Integer result, String associationID) {
        Integer res = schoolMapper.schoolagreeassociation(result, associationID);
        if (res > 0) {
            return "操作成功！";
        }
        return "操作失败！";
    }
}
