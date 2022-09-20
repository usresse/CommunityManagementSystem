package com.example.communitymanagementsystem.service.implem;

import com.example.communitymanagementsystem.Mapper.brean.PersonalBean;
import com.example.communitymanagementsystem.Mapper.brean.SchoolMajorBean;
import com.example.communitymanagementsystem.mybatis.mappers.PersonalMapper;
import com.example.communitymanagementsystem.service.inter.HandleServer;
import com.example.communitymanagementsystem.service.inter.SchoolServer;
import com.example.communitymanagementsystem.service.utils.UtilsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * \* @author: Predator
 * \* Date: 2022-9-2
 * \* Time: 19:10
 * \* Description:
 * \
 */
@Service
public class HandleImplement implements HandleServer {
    @Autowired
    private PersonalMapper personalMapper;

    @Autowired
    private SchoolServer schoolServer;

    /**
     * @param ： number
     * @return com.example.communitymanagementsystem.Mapper.brean.PersonalBean
     * Description:查询一条指定的number数据
     * @author Predator
     * @date 2022-9-2 19:12
     */
    @Override
    public PersonalBean select(String number) {
        PersonalBean personalBean = personalMapper.selectAll(number);
        /**对图片地址的定位*/
        String url = personalBean.getBold();
        personalBean.setBold("..\\img\\" + number + "\\" + url);
        return personalBean;
    }

    /**
     * @param ：
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * Description:查询所有专业
     * @author Predator
     * @date 2022-7-10 14:12
     */
    @Override
    public List<SchoolMajorBean> selectschoolMajor() {
        return schoolServer.selectschoolMajor();
    }

    /**
     * @author Predator
     * @date 2022-9-3 15:25
     * @param ： personalBean
     * @return java.lang.String
     * Description:修改数据的操作
     */
    @Override
    public String updata(PersonalBean personalBean) {
        int result2 = personalMapper.updata(personalBean);
        if (result2 > 0) {
            return "修改成功!";
        } else {
            return "修改失败!";
        }
    }

    /**
     * @param ： fileName
     * @param ： number
     * @return void
     * Description:插入图片，保存到数据库
     * @author Predator
     * @date 2022-7-7 21:29
     */
    @Override
    public String updataBold(String fileName, String number) {
        if (personalMapper.updataBold(fileName, Integer.parseInt(number)) > 0) {
            return "头像更新成功！";
        } else {
            return "头像更新失败！";
        }
    }
}
