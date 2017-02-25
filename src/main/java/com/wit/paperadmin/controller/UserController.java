package com.wit.paperadmin.controller;

import com.wit.paperadmin.model.UserInfoData;
import com.wit.paperadmin.pojo.BaseResponse;
import com.wit.paperadmin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 何鹏帅 on 2016/11/26.
 */
@Controller
public class UserController {

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
}