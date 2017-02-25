package com.wit.paperadmin.mapper;

import com.wit.paperadmin.model.UserInfoData;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserInfoDataMapper extends Mapper<UserInfoData> {
    int getUserCount();
    int getUserIDByUsername(String username);
}