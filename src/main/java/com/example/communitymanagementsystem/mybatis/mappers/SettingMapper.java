package com.example.communitymanagementsystem.mybatis.mappers;

import com.example.communitymanagementsystem.Mapper.brean.PersonalBean;
import org.apache.ibatis.annotations.Mapper;

/**
 * \* @author: Predator
 * \* Date: 2022-9-7
 * \* Time: 18:15
 * \* Description:
 * \
 */
@Mapper
public interface SettingMapper {
    String selectAssociationNumber(String associationNumber);

    String selectMajor(String major);

    PersonalBean personal(String number);
}
