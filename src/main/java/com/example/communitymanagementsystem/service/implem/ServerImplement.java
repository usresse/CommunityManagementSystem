package com.example.communitymanagementsystem.service.implem;

import com.example.communitymanagementsystem.Mapper.brean.PersonalBean;
import com.example.communitymanagementsystem.mybatis.mappers.PersonalMapper;
import com.example.communitymanagementsystem.mybatis.mappers.schoolMajorMapper;
import com.example.communitymanagementsystem.service.inter.Server;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * \* @author: Predator
 * \* Date: 2022-7-4
 * \* Time: 9:27
 * \* Description:
 * \服务dao
 */
@Service
public class ServerImplement implements Server {
    @Autowired
    private PersonalMapper personalMapper;

    @Autowired
    private schoolMajorMapper schoolMajorMapper;

    /**
     * @param ： number
     * @return com.example.communitymanagementsystem.Mapper.brean.PersonalBean
     * Description:查询制定一条数据
     * @author Predator
     * @date 2022-7-4 21:06
     */
    @Override
    public PersonalBean select(String number) {
        PersonalBean personalBean = personalMapper.select(number);

        if (personalBean == null) {
            personalBean = personalMapper.selectAll(number);
        }

        /**对图片地址的定位*/
        String url = personalBean.getBold();
        personalBean.setBold("..\\img\\" + number + "\\" + url);

        return personalBean;
    }

    /**
     * @param ： null
     * @return Description:查询成员分页查询每页6人
     * @author Predator
     * @date 2022-7-5 15:13
     */
    @Override
    public PageInfo PagingQuery(Integer index, String condition, String number) {
        /**获取到第几页，每页的个数*/
        PageHelper.startPage(index, 6);
        List<PersonalBean> list;



        if (condition == null || condition.equals("null")) {
            list = personalMapper.selectAssociation(number);
        } else {
            String[] data = condition.split(":");
            System.out.println(condition);
            System.out.println(data[0] + "===" + data[1]);
            Object o = null;
            switch (data[0]) {
                case "number":
                case "sex":
                case "telephone":
                    o = Integer.parseInt(data[1]);
                    break;
                case "name":
                case "dateOfBirth":
                case "major":
                case "dateOfRegistration":
                    o = data[1];
                    break;
            }

            list = personalMapper.conditionQuery(data[0], o, number);

            for (PersonalBean i : list) {
                System.out.println(i);
            }
        }

        /**获取到封装的一组数据*/
        PageInfo pageInfo = new PageInfo<>(list, 50);
        return pageInfo;
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

    /**
     * @param ： map
     * @return java.lang.Boolean
     * Description:修改数据的操作
     * @author Predator
     * @date 2022-7-4 17:51
     */
    @Override
    @Transactional
    public Boolean updata(Map<String, Object> map) {
        int result2 = personalMapper.updata(map);
        if (result2 > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param ：
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * Description:查询所有专业
     * @author Predator
     * @date 2022-7-10 14:12
     */
    @Override
    public List<Map<String, Object>> selectschoolMajor() {
        return schoolMajorMapper.selectAll();
    }


}
