package com.example.communitymanagementsystem.service.implem;

import com.example.communitymanagementsystem.Mapper.brean.PersonalBean;
import com.example.communitymanagementsystem.mybatis.mappers.SettingMapper;
import com.example.communitymanagementsystem.service.inter.SettingServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * \* @author: Predator
 * \* Date: 2022-9-7
 * \* Time: 18:11
 * \* Description:
 * \
 */
@Service
public class SettingImplement implements SettingServer {
    @Autowired
    private SettingMapper settingMapper;

    /**
     * @param ： number
     * @return java.lang.String
     * Description:获取用户社团名
     * @author Predator
     * @date 2022-9-6 15:34
     */
    @Override
    public String associationNumber(String associationNumber) {
        return settingMapper.selectAssociationNumber(associationNumber);
    }

    /**
     * @param ： number
     * @return java.lang.String
     * Description:获取用户社团职位
     * @author Predator
     * @date 2022-9-6 15:34
     */
    @Override
    public String major(String major) {
        return settingMapper.selectMajor(major);
    }

    @Override
    public String personal(String key, String number) {
        PersonalBean personalBean = settingMapper.personal(number);
        try {
            //获取已经声明的字段属性 ===》getDeclaredField=加载类的所有成员（包括私有）
            Field field = PersonalBean.class.getDeclaredField(key);
            //私有属性访问===》设置可访问
            field.setAccessible(true);
            //获取那个对象中的属性值
            return field.get(personalBean).toString();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
