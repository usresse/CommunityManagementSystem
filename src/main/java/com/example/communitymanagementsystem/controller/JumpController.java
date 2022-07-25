package com.example.communitymanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * \* @author: Predator
 * \* Date: 2022-7-2
 * \* Time: 14:45
 * \* Description:
 * \简单的页面跳转显示
 */
@Controller
public class JumpController {

    /********几个内页面的显示**********************************************/

    @RequestMapping("MyClubStatus/CommunityPersonnelManagement")
    public String first2() {
       return "html/CommunityPersonnelManagement";
    }

    @RequestMapping("MyClubStatus/2")
    public String firs2() {
        return "html/2";
    }

    /**********菜单栏的显示****************************************************/

    @RequestMapping("/caidan")
    public String caidan() {
        return "caidan";
    }

    /************内页面的主要显示************************************************************/
    @RequestMapping("/nei")
    public String nei() {
        return "nei";
    }

    /**********登录页面的跳转********************************************************************/
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /************注册页面的跳转*************************************************************/
    @RequestMapping("/register")
    public String register() {
        return "register";
    }


}
