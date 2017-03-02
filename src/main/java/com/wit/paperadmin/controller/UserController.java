package com.wit.paperadmin.controller;

import com.wit.paperadmin.model.ManagerInfoData;
import com.wit.paperadmin.model.UserInfoData;
import com.wit.paperadmin.pojo.BaseResponse;
import com.wit.paperadmin.service.UserService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by 何鹏帅 on 2016/11/26.
 */
@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param userInfoData
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    @ResponseBody
    public BaseResponse login(@ModelAttribute("userInfoData") UserInfoData userInfoData) throws Exception{
        BaseResponse msg = new BaseResponse();
        int ret = userService.login(userInfoData);
        if(ret == 0) {
            msg.setCode(0);
            msg.setMessage("用户名或密码错误，请重新输入");
        } else {
            msg.setCode(1);
            msg.setMessage("登录成功");
        }
        return msg;
    }

    @RequestMapping("/manager_login")
    @ResponseBody
    public BaseResponse managerLogin(@ModelAttribute("managerInfoDate")ManagerInfoData managerInfoData) {
        BaseResponse msg = new BaseResponse();
        int ret = userService.managerLogin(managerInfoData);
        if(ret == 0) {
            msg.setCode(0);
            msg.setMessage("用户名或密码错误，请重新输入");
        } else {
            msg.setCode(1);
            msg.setMessage("登录成功");
        }
        return msg;
    }

    /**
     * 修改密码
     * @param userInfoData
     * @param newpassword
     * @return
     */
    @RequestMapping("/change_password")
    @ResponseBody
    public BaseResponse changePassword(@ModelAttribute("userInfoData") UserInfoData userInfoData, @RequestParam String newpassword) {
        BaseResponse msg = new BaseResponse();
        int ret = userService.changePassword(userInfoData, newpassword);

        if(ret == 0) {
            msg.setCode(0);
            msg.setMessage("原密码输入错误");
        } else {
            msg.setCode(1);
            msg.setMessage("密码修改成功");
        }
        return msg;
    }

    /**
     * 管理员修改密码
     * @param managerInfoData
     * @param newpassword
     * @return
     */
    @RequestMapping("/manager_change_password")
    @ResponseBody
    public BaseResponse managerChangePassword(@ModelAttribute("managerInfoData") ManagerInfoData managerInfoData, @RequestParam String newpassword) {
        BaseResponse msg = new BaseResponse();
        int ret = userService.manageChangePassword(managerInfoData, newpassword);

        if(ret == 0) {
            msg.setCode(0);
            msg.setMessage("原密码输入错误");
        } else {
            msg.setCode(1);
            msg.setMessage("密码修改成功");
        }
        return msg;
    }
}
