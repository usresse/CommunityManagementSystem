package com.example.communitymanagementsystem.service.inter;

import com.example.communitymanagementsystem.Mapper.brean.PersonalBean;
import org.springframework.web.servlet.ModelAndView;

/**
 * \* @author: Predator
 * \* Date: 2022-7-2
 * \* Time: 13:21
 * \* Description:
 * \
 */
public interface LoginServer {
    ModelAndView Validity(String number, String password);

    String insert(PersonalBean personalBean);
}
