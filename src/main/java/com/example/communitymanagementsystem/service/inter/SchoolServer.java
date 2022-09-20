package com.example.communitymanagementsystem.service.inter;

import com.example.communitymanagementsystem.Mapper.brean.SchoolMajorBean;

import java.util.List;
import java.util.Map;

/**
 * \* @author: Predator
 * \* Date: 2022-7-14
 * \* Time: 22:30
 * \* Description:
 * \
 */
public interface SchoolServer {

    Boolean login(Map<String, Object> map);

    List<SchoolMajorBean> selectschoolMajor();

    List<Map<String,Object>> activity();

    String requset(Integer result,Integer ID);

    List<Map<String, Object>> selectApplyAssociation();

    String schoolagreeassociation(Integer result, String associationID);
}
