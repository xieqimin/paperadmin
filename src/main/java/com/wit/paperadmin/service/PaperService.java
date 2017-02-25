package com.wit.paperadmin.service;

import com.wit.paperadmin.Utils.AESUtils;
import com.wit.paperadmin.Utils.SHAUtils;
import com.wit.paperadmin.mapper.PaperInfoDataMapper;
import com.wit.paperadmin.mapper.PaperKeyDataMapper;
import com.wit.paperadmin.mapper.UserInfoDataMapper;
import com.wit.paperadmin.model.PaperInfoData;
import com.wit.paperadmin.model.PaperKeyData;
import com.wit.paperadmin.model.UserInfoData;
import com.wit.paperadmin.pojo.PaperFileInfo;
import com.wit.paperadmin.pojo.PaperInfoVo;
import com.wit.paperadmin.pojo.SearchVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by 何鹏帅 on 2016/11/27.
 */
@Service
public class PaperService {
    private Logger logger = LoggerFactory.getLogger(PaperService.class);

    @Autowired
    private UserInfoDataMapper uidMapper;

    @Autowired
    private PaperInfoDataMapper pidMapper;

    @Autowired
    private PaperKeyDataMapper pkdMapper;

    /**
     * 试卷上传
     * @param file
     * @param paperInfoData
     * @param username
     * @param session
     * @return
     * @throws Exception
     */
    public int storePaper(MultipartFile file, PaperInfoData paperInfoData, String username, HttpSession session) throws Exception {
        String root = session.getServletContext().getRealPath("/") + "paperFile\\" + username;

        File file1 = new File(root);

        //如果目录不存在，则创建目录
        if(!file1.exists()  && !file1 .isDirectory()) {
            file1.mkdirs();
        }

        // 保存目标文件路径
        String filePath = root + "\\" + System.currentTimeMillis()/1000;

        // 临时文件路径
        String temp = root + "\\" + file.getName();

        file.transferTo(new File(temp));

        // 对文件加密
        String key = AESUtils.getSecretKey();
        String shaKey = SHAUtils.getSha1(key);
        String encryptKey = AESUtils.getSecretKey(shaKey);
        AESUtils.encryptFile(encryptKey, temp, filePath);

        // 删除原文件
        File tep = new File(temp);
        tep.delete();

        // 设置文件路径
        paperInfoData.setSaveaddress(filePath);

        // 获取用户ID
        UserInfoData userInfoData = new UserInfoData();
        userInfoData.setUsername(username);
        userInfoData = uidMapper.selectOne(userInfoData);

        // 设置用户ID
        paperInfoData.setUserid(userInfoData.getId());

        // 获取当前时间，并设置试卷上传时间
        Long ab =  System.currentTimeMillis()/1000;
        int uploadTime = Integer.valueOf(ab.toString());
        paperInfoData.setUploadtime(uploadTime);

        // 设置试卷状态
        paperInfoData.setPaperstatus(1);

        // 保存试卷信息
        pidMapper.insert(paperInfoData);

        paperInfoData = pidMapper.selectOne(paperInfoData);

        // 保存密钥信息
        PaperKeyData paperKeyData = new PaperKeyData();
        paperKeyData.setKeycontent(key);
        paperKeyData.setPaperid(paperInfoData.getId());
        pkdMapper.insert(paperKeyData);
        return 1;
    }

    /**
     * 根据用户名获取试卷列表总页数
     * @param username
     * @param pagesize
     * @return
     */
    public int getTotalPageNum(String username, int pagesize) {
        // 根据用户名获取用户ID
        int userID = uidMapper.getUserIDByUsername(username);

        // 根据用户ID获取该用户上传试卷数
        int paperCount = pidMapper.getPaperCountByUserID(userID);

        int totalpage = paperCount / pagesize;
        return pagesize * totalpage == paperCount ? totalpage : totalpage + 1;
    }

    /**
     * 获取某个学期的试卷列表
     * @param username
     * @param schoolyearID
     * @param term
     * @return
     */
    public List<PaperInfoVo> getPaperListByTerm(String username, int schoolyearID, String term) {
        // 根据用户名获取用户ID
        int userID = uidMapper.getUserIDByUsername(username);
        return pidMapper.getPaperListByTerm(userID, schoolyearID, term);
    }

    /**
     * 根据试卷ID判断是否可以修改试卷信息
     * @param paperID
     * @return
     */
    public int allowModify(int paperID) {
        PaperInfoData piData = pidMapper.selectByPrimaryKey(paperID);

        if(piData.getPaperstatus() > 1) {
            return 0;
        }
        return 1;
    }

    /**
     * 根据试卷ID获取试卷信息
     * @param paperID
     * @return
     */
    public PaperInfoData getPaperInfoByID(int paperID) {
        return pidMapper.selectByPrimaryKey(paperID);
    }

    /**
     * 修改试卷信息
     * @param paperInfoData
     * @return
     */
    public int modifyPaperInfo(PaperInfoData paperInfoData) {

        // 获取当前时间，并设置试卷上传修改时间
        Long ab =  System.currentTimeMillis()/1000;
        int uploadTime = Integer.valueOf(ab.toString());
        paperInfoData.setUploadtime(uploadTime);
        pidMapper.updateByPrimaryKey(paperInfoData);
        return 1;
    }

    /**
     * 根据试卷ID删除试卷及其相关信息
     * @param paperID
     * @return
     */
    public int deletePaperByID(int paperID) {
        PaperKeyData keyData = new PaperKeyData();

        // 根据试卷ID删除密钥
        keyData.setPaperid(paperID);
        keyData.setId(null);
        pkdMapper.delete(keyData);

        PaperInfoData piData = pidMapper.selectByPrimaryKey(paperID);
        String path = piData.getSaveaddress();
        File paperFile = new File(path);

        // 删除试卷文件
        if(paperFile.exists()) {
            paperFile.delete();
        }

        // 从试卷列表删除试卷信息
        pidMapper.deleteByPrimaryKey(paperID);
        return 1;
    }

    /**
     * 根据条件查询试卷列表
     * @param searchVo
     * @return
     */
    public List<PaperInfoVo> getPaperListByCondition(SearchVo searchVo) {
        if(searchVo.getProfession().equals("全部")) {
            searchVo.setProfession(null);
        }

        if(searchVo.getInstitute().equals("全部")) {
            searchVo.setInstituteid(null);
        }

        if(searchVo.getPaperstatus().equals("全部")) {
            searchVo.setPaperstatusid(null);
        }

        List<PaperInfoVo> paperInfoVos = pidMapper.getPaperListByCondition(searchVo);
        for (PaperInfoVo piVo: paperInfoVos) {

            // 如果取卷时间为null,则将其设为0
            if(piVo.getReceivetime() == null) {
                piVo.setReceivetime(0);
            }
        }

        return paperInfoVos;
    }
}
