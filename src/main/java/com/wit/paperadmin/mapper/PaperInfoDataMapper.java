package com.wit.paperadmin.mapper;

import com.wit.paperadmin.model.PaperInfoData;
import com.wit.paperadmin.pojo.PaperInfoVo;
import com.wit.paperadmin.pojo.SearchVo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface PaperInfoDataMapper extends Mapper<PaperInfoData> {
    int getPaperCountByUserID(int userID);
    List<PaperInfoVo> getPaperListByTerm(int userID, int schoolyearID, String term);
    List<String> getProfessionByInstituteID(int instituteID);
    List<PaperInfoVo> getPaperListByCondition(SearchVo searchVo);
}