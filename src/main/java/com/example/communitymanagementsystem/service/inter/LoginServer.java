package com.example.communitymanagementsystem.service.inter;

import com.example.communitymanagementsystem.Mapper.brean.PersonalBean;

/**
 * \* @author: Predator
 * \* Date: 2022-7-2
 * \* Time: 13:21
 * \* Description:
 * \
 */
public interface LoginServer {
    PersonalBean All(String number, String password);

    Boolean insert(PersonalBean personalBean);
}
