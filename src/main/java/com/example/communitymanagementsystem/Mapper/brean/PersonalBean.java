package com.example.communitymanagementsystem.Mapper.brean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * \* @author: Predator
 * \* Date: 2022-7-3
 * \* Time: 17:18
 * \* Description:
 * \个人信息实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class PersonalBean {
    /**学号*/
    private Integer studentNumber;
    /**账号*/
    private String number;
    /**姓名*/
    private String Name;
    /**密码*/
    private String password;
    /**昵称*/
    private String nickName;
    /**性别*/
    private Integer sex;
    /**出生日期*/
    @DateTimeFormat(style = "yyyy-MM-dd")
    private Date dateOfBirth;
    /**电话号码*/
    private String Telephone;
    /**邮箱*/
    private String mailbox;
    /**职业*/
    private String major;
    /**社团名称*/
    private String associationName;
    /**学校专业*/
    private String schoolMajor;
    /**照片*/
    private String bold;
    /**注册时间*/
    @DateTimeFormat(style = "yyyy-MM-dd")
    private Date dateOfRegistration;
}
