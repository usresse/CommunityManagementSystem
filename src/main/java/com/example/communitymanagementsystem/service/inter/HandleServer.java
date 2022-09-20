package com.example.communitymanagementsystem.service.inter;

import com.example.communitymanagementsystem.Mapper.brean.PersonalBean;
import com.example.communitymanagementsystem.Mapper.brean.SchoolMajorBean;

import java.util.List;

/**
 * \* @author: Predator
 * \* Date: 2022-9-2
 * \* Time: 19:10
 * \* Description:
 * \
 */
public interface HandleServer {

    PersonalBean select(String number);

    List<SchoolMajorBean> selectschoolMajor();

    String updata(PersonalBean personalBean);

    String updataBold(String fileName, String number);
}
