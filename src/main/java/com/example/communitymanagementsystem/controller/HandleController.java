package com.example.communitymanagementsystem.controller;

import com.example.communitymanagementsystem.Mapper.brean.PersonalBean;
import com.example.communitymanagementsystem.Mapper.brean.SchoolMajorBean;
import com.example.communitymanagementsystem.service.inter.HandleServer;
import com.example.communitymanagementsystem.service.utils.UtilsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * \* @author: Predator
 * \* Date: 2022-6-23
 * \* Time: 20:45
 * \* Description:
 * \页面的效验处理接收发送数据
 */
@Controller
public class HandleController {
    @Autowired
    private HandleServer handleServer;

    /**
     * @param ：
     * @return java.lang.String
     * Description:个人信息的显示
     * @author Predator
     * @date 2022-7-3 18:30
     */
    @RequestMapping("/setting")
    public ModelAndView setting() {
        ModelAndView modelAndView = new ModelAndView("html/setting");
        return modelAndView;
    }

    /**
     * @author Predator
     * @date 2022-9-5 20:23
     * @param ：
     * @return java.util.List<com.example.communitymanagementsystem.Mapper.brean.SchoolMajorBean>
     * Description:学校专业的数据查询
     */
    @GetMapping("schoolMajor")
    @ResponseBody
    public List<SchoolMajorBean> schoolMajor() {
        return handleServer.selectschoolMajor();
    }

    /**
     * @param ：
     * @return java.lang.String
     * Description:个人信息的显示
     * @author Predator
     * @date 2022-7-3 18:30
     */

    @PostMapping ("settingInsert")
    @ResponseBody
    public String settingInsert(PersonalBean personalBean) {
       return handleServer.updata(personalBean);
    }

    /**
     * @param ： multipartFile
     * @param ： number
     * @return java.lang.String
     * Description:对于传头像图片的操作
     * @author Predator
     * @date 2022-7-4 17:51
     */
    @RequestMapping("settingFile")
    @ResponseBody
    public String settingFile(@RequestPart("file") MultipartFile multipartFile,
                              @RequestParam("number") String number) {

        if (!multipartFile.isEmpty()) {

            /**创建用户头像存储文件*/
            File file = new File("D:\\1学习文件\\毕业设计\\CommunityManagementSystem\\src\\main" +
                    "\\resources\\static\\img\\" + number);
            /*创建文件夹*/
            if (!file.exists()) {
                file.mkdirs();
            }

            /**判断用户头像是否是图片格式*/
            String fileName = multipartFile.getOriginalFilename();
            if (!UtilsServer.FormatServer(fileName)) {
                return "格式错误！";
            }

            /****************
             *ClassUtils.getDefaultClassLoader().getResource("").getPath()
             * 的路径显示为下
             *D:/1学习文件/毕业设计/CommunityManagementSystem/target/classes/
             */
            //File file = new File(ClassUtils.getDefaultClassLoader().getResource("").getPath()
            //        + "static\\data\\img\\" + number + "\\" + fileName);

            /**判断照片是否已经存在，存在则直接将名字保存到数据库*/
            File file1 = new File(file, fileName);
            if (file1.exists()) {
                return "头像更新成功！";
            }

            try {
                /**删除之前的照片*/
                File[] files = file.listFiles();
                for (File fi : files) {
                    fi.delete();
                }

                /*transferTo使用绝对路径，如果使用相对路径方法会自动生成一个父路径导致路径错误！*/
                multipartFile.transferTo(file1);
                return handleServer.updataBold(fileName, number);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "头像上传失败！";
    }


}
