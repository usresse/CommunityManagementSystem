package com.example.communitymanagementsystem.service.inter;

import com.example.communitymanagementsystem.Mapper.brean.PersonalBean;

/**
 * \* @author: Predator
 * \* Date: 2022-9-7
 * \* Time: 18:11
 * \* Description:
 * \
 */
public interface SettingServer {
    String associationNumber(String associationNumber);

    String major(String major);

    String personal(String key, String number);

    PersonalBean personal_number(String number);
}
