package com.example.communitymanagementsystem;

import com.example.communitymanagementsystem.Mapper.brean.PersonalBean;
import com.example.communitymanagementsystem.mybatis.mappers.AssociationMapper;
import com.example.communitymanagementsystem.mybatis.mappers.PersonalMapper;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
import com.example.communitymanagementsystem.service.utils.UtilsServer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
class CommunityManagementSystemApplicationTests {

    @Autowired
    private PersonalMapper personalMapper;

    @Autowired
    private AssociationMapper associationMapper;

    @Test
    void contextLoads() {
        String[] data ={"1","2","3","4","5","6"};
        System.out.println(UtilsServer.CombinationRemove(data,5));

        //Map<String, Object> map = associationMapper.selectApply("32654");
        //for (String key :map.keySet()) {
        //    System.out.println(key+"=="+map.get(key));
        //}

        //PageHelper.startPage(0,1);
        //List<PersonalBean> list = personalMapper.select();
        //PageInfo<PersonalBean> pageInfo = new PageInfo<>(list,5);
        //System.out.println(pageInfo);
        //
        //for(PersonalBean map : list){
        //    System.out.println(map);
        //}
    }

}
