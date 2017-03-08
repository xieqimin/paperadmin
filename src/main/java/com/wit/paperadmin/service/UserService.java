package com.wit.paperadmin.service;

import com.wit.paperadmin.Utils.Md5Wrapper;
import com.wit.paperadmin.mapper.ManagerInfoDataMapper;
import com.wit.paperadmin.mapper.UserInfoDataMapper;
import com.wit.paperadmin.model.ManagerInfoData;
import com.wit.paperadmin.model.UserInfoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 何鹏帅 on 2016/11/26.
 */
@Service
public class UserService {

    @Autowired
    private UserInfoDataMapper userInfoDataMapper;

    @Autowired
    private ManagerInfoDataMapper midMapper;

    /**
     * 用户登录，成功返回1
     * 用户名或密码错误返回0
     * @param userInfoData
     * @return
     */
    public int login(UserInfoData userInfoData) {
        String password = Md5Wrapper.getMD5(userInfoData.getPassword());
        userInfoData.setPassword(password);
        return userInfoDataMapper.selectCount(userInfoData);
    }

    /**
     * 教务处登录，成功返回1
     * 用户名或密码错误返回0
     * @param managerInfoData
     * @return
     */
    public int managerLogin(ManagerInfoData managerInfoData) {
        String passoword = Md5Wrapper.getMD5(managerInfoData.getPassword());
        managerInfoData.setPassword(passoword);
        return midMapper.selectCount(managerInfoData);
    }

    /**
     * 修改密码
     * @param userInfoData
     * @param newPassword
     * @return
     */
    public int changePassword(UserInfoData userInfoData, String newPassword) {
        String password = Md5Wrapper.getMD5(userInfoData.getPassword());
        userInfoData.setPassword(password);
        List<UserInfoData> users = userInfoDataMapper.select(userInfoData);

        if(users.size() == 0) {
            return 0;
        } else {
            String newPwd = Md5Wrapper.getMD5(newPassword);
            userInfoData = users.get(0);
            userInfoData.setPassword(newPwd);
            userInfoDataMapper.updateByPrimaryKey(userInfoData);
            return 1;
        }
    }

    /**
     * 管理员修改密码
     * @param managerInfoData
     * @param newPassword
     * @return
     */
    public int manageChangePassword(ManagerInfoData managerInfoData, String newPassword) {
        String password = Md5Wrapper.getMD5(managerInfoData.getPassword());
        managerInfoData.setPassword(password);
        List<ManagerInfoData> users = midMapper.select(managerInfoData);

        if(users.size() == 0) {
            return 0;
        } else {
            String newPwd = Md5Wrapper.getMD5(newPassword);
            managerInfoData = users.get(0);
            managerInfoData.setPassword(newPwd);
            midMapper.updateByPrimaryKey(managerInfoData);
            return 1;
        }
    }
}
