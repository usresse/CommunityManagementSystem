package com.example.communitymanagementsystem.service.inter;

import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * \* @author: Predator
 * \* Date: 2022-7-11
 * \* Time: 11:48
 * \* Description:
 * \
 */
public interface AssociationServer {
    PageInfo selectAll(Integer index);

    PageInfo select(Integer index ,String[] data);

    String create(String[] data);

    Map<String,Object> select(String number);

    Boolean delete(Integer number);

    PageInfo reviewed(String number,Integer index);

    Boolean judge(Integer index, String applyAssociation, String number);

    String Create(String number, Map<String, Object> data);

    String KickOut(String number);

    Map<String, Object> AssociationApplication(String associationID);

    Map<String, Object> MoveAssociationApplication(String[] data);
}
