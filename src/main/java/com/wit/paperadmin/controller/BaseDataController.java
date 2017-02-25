package com.wit.paperadmin.controller;

import com.wit.paperadmin.model.InstituteData;
import com.wit.paperadmin.model.PaperStatusData;
import com.wit.paperadmin.model.SchoolYearData;
import com.wit.paperadmin.service.BaseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 何鹏帅 on 2016/11/27.
 */
@Controller
public class BaseDataController {

    @Autowired
    private BaseDataService baseDataService;

    /**
     * 获取学年信息
     * @return
     */
    @RequestMapping("/get_school_years")
    @ResponseBody
    public SchoolYearData[] getSchoolYears() {
        return baseDataService.getSchoolYears();
    }


    @RequestMapping("/get_institutes")
    @ResponseBody
    public InstituteData[] getInstitutes() {
        return baseDataService.getInstitutes();
    }

    @RequestMapping("/get_professions")
    @ResponseBody
    public List<String> getProfessions(int instituteID) throws Exception {
        return baseDataService.getProfessions(instituteID);
    }

    @RequestMapping("/get_paper_status")
    @ResponseBody
    public List<PaperStatusData> getPaperStatus() {
        return baseDataService.getPaperStatus();
    }
    /**
     * 检查当前学年是否存在，
     * 如果不存在，则插入
     * @param schoolyear
     */
    @RequestMapping("/check_current_school_year")
    @ResponseBody
    public int checkCurrentSchoolYear(@RequestParam String schoolyear) {
        baseDataService.checkCurrentSchoolYear(schoolyear);
        return 1;
    }
}
