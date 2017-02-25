package com.wit.paperadmin.controller;

import com.wit.paperadmin.mapper.UserInfoDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 何鹏帅 on 2016/11/26.
 */
@Controller
public class HomeController {
    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/")
    public String homePage() { return "index"; }
}
