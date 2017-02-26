package com.wit.paperadmin.controller;

import com.wit.paperadmin.model.PaperInfoData;
import com.wit.paperadmin.pojo.BaseResponse;
import com.wit.paperadmin.pojo.PaperFileInfo;
import com.wit.paperadmin.pojo.PaperInfoVo;
import com.wit.paperadmin.pojo.SearchVo;
import com.wit.paperadmin.service.PaperService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by 何鹏帅 on 2016/11/27.
 */
@Controller
public class PaperController {
    Logger logger = LoggerFactory.getLogger(PaperController.class);

    @Autowired
    private PaperService paperService;

    /**
     * 用户上传试卷
     * @param file  试卷文件
     * @param username 用户名
     * @param paperInfoData 试卷信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/upload1")
    @ResponseBody
    public BaseResponse paperUpload(@RequestParam MultipartFile file, String username, @ModelAttribute("paperInfoData") PaperInfoData paperInfoData, HttpSession session)
    throws Exception {
        paperService.storePaper(file,paperInfoData,username, session);
        BaseResponse msg = new BaseResponse();
        msg.setCode(1);
        msg.setMessage("提交成功");
        return msg;
    }

    /**
     * 根据用户名获取试卷列表总页数
     * @param username
     * @param pagesize
     * @return
     */
    @RequestMapping("/get_total_page_num")
    @ResponseBody
    public int getTotalPageNum(@RequestParam String username, @RequestParam int pagesize) {
        return paperService.getTotalPageNum(username,pagesize);
    }

    /**
     * 获取某个学期的试卷列表
     * @param username
     * @param schoolyear
     * @param term
     * @return
     */
    @RequestMapping("/get_paper_list_by_term")
    @ResponseBody
    public List<PaperInfoVo> getPaperListByTerm(@RequestParam String username, @RequestParam int schoolyear, @RequestParam String term) {
        return  paperService.getPaperListByTerm(username, schoolyear, term);
    }

    /**
     * 判断试卷信息是否可以修改
     * @param paperID
     * @return
     */
    @RequestMapping("/allow_modify")
    @ResponseBody
    public BaseResponse allowModify(@RequestParam int paperID) {
        BaseResponse msg = new BaseResponse();
        int ret = paperService.allowModify(paperID);

        if(ret == 0) {
            msg.setCode(0);
            msg.setMessage("试卷已送印，不允许再修改试卷信息");
        } else {
            msg.setCode(1);
        }
        return msg;
    }

    /**
     * 根据试卷ID获取试卷信息
     * @param paperID
     * @return
     */
    @RequestMapping("/get_paper_info_by_id")
    @ResponseBody
    public PaperInfoData getPaperInfoByID(int paperID) {
        return paperService.getPaperInfoByID(paperID);
    }

    /**
     * 修改试卷信息
     * @param paperInfo
     * @return
     */
    @RequestMapping("/modify_paper_info")
    @ResponseBody
    public BaseResponse modifyPaperInfo(@RequestBody PaperInfoData paperInfo) {
        BaseResponse msg = new BaseResponse();

        int ret = paperService.modifyPaperInfo(paperInfo);
        if(ret == 1) {
            msg.setCode(1);
            msg.setMessage("试卷信息修改成功");
        } else {
            msg.setCode(0);
            msg.setMessage("试卷信息修改失败");
        }
        return msg;
    }

    /**
     * 根据试卷ID删除试卷及其相关信息
     * @param paperID
     * @return
     */
    @RequestMapping("/delete_paper_by_id")
    @ResponseBody
    public BaseResponse deletePaperByID(@RequestParam int paperID) {
        BaseResponse msg = new BaseResponse();

        int ret = paperService.deletePaperByID(paperID);
        if(ret == 1) {
            msg.setCode(1);
        } else {
            msg.setCode(0);
        }
        return msg;
    }

    /**
     * 根据条件查询试卷列表
     * @param searchVo
     * @return
     */
    @RequestMapping("/get_paperlist_by_condition")
    @ResponseBody
    public List<PaperInfoVo> getPaperListByCondition(@RequestBody SearchVo searchVo) {
        return paperService.getPaperListByCondition(searchVo);
    }

    /**
     * 管理员更新试卷信息
     * @param paperInfoData
     * @return
     */
    @RequestMapping("/update_paper_info")
    @ResponseBody
    public BaseResponse updatePaperInfo(@RequestBody PaperInfoData paperInfoData) {
        BaseResponse msg = new BaseResponse();
        int ret = paperService.updatePaperInfo(paperInfoData);

        if(ret == 1) {
            msg.setCode(1);
        } else {
            msg.setCode(0);
        }

        return msg;
    }
}
