package com.example.communitymanagementsystem.service.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * \* @author: Predator
 * \* Date: 2022-7-4
 * \* Time: 18:32
 * \* Description:
 * \
 */
public class UtilsServer {

    /**
     * @param ：
     * @return 私有化构造方法，不创建对象
     * Description:
     * @author Predator
     * @date 2022-7-16 12:11
     */
    private UtilsServer() {

    }

    /**
     * @param ： name
     * @return java.lang.Boolean
     * Description:判断头像格式是否正确
     * @author Predator
     * @date 2022-7-4 18:36
     */
    public static Boolean FormatServer(String name) {
        if ("jpg png jpeg pdf gif".contains(name.substring(name.lastIndexOf(".") + 1))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param ： data==>查询出来的id-id形式的
     * @param ： ID==》需要判断是否添加的id
     * @return java.lang.Integer
     * Description:判断活动中查询出来的字符串id-id形式进行判断
     * @author Predator
     * @date 2022-7-16 12:14
     */
    public static Integer IDExists(String[] data, Integer ID) {
        int index = -1;
        for (int i = 0; i < data.length; i++) {
            if (ID.toString().equals(data[i])) {
                return i;
            }
        }
        return index;
    }

    /**
     * @param ： data==》数组
     * @param ： index==>不需要的系数
     * @return java.lang.String
     * Description:
     * @author Predator
     * @date 2022-7-16 15:11
     */
    public static String CombinationRemove(String[] data, int index) {
        String result = null;

        /**将字符串转换成集合进行连接成字符串*/
        List<String> list = new ArrayList<>(Arrays.asList(data));

        list.remove(index);
        /**移除最后一位的时候*/
        if(list.size() == 0){
            return result;
        }
        result = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            result += "-" + list.get(i);
        }
        return result;
    }
}
