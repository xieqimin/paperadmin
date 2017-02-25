package com.wit.paperadmin.service;

import com.wit.paperadmin.mapper.InstituteDataMapper;
import com.wit.paperadmin.mapper.PaperInfoDataMapper;
import com.wit.paperadmin.mapper.PaperStatusDataMapper;
import com.wit.paperadmin.mapper.SchoolYearDataMapper;
import com.wit.paperadmin.model.InstituteData;
import com.wit.paperadmin.model.PaperStatusData;
import com.wit.paperadmin.model.SchoolYearData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 何鹏帅 on 2016/11/27.
 */
@Service
public class BaseDataService {
    @Autowired
    private SchoolYearDataMapper sydMapper;

    @Autowired
    private InstituteDataMapper idMapper;

    @Autowired
    private PaperInfoDataMapper pidMapper;

    @Autowired
    private PaperStatusDataMapper psdMapper;

    public SchoolYearData[] getSchoolYears() {
        List<SchoolYearData> schoolYearDatas = sydMapper.selectAll();
        return  (SchoolYearData[])schoolYearDatas.toArray(new SchoolYearData[schoolYearDatas.size()]);
    }

    public InstituteData[] getInstitutes() {
        List<InstituteData> instituteDatas = idMapper.selectAll();
        return (InstituteData[])instituteDatas.toArray(new InstituteData[instituteDatas.size()]);
    }

    public List<String> getProfessions(int instituteID) throws Exception {

        return pidMapper.getProfessionByInstituteID(instituteID);
    }

    public List<PaperStatusData> getPaperStatus() {
        return psdMapper.selectAll();
    }
    /**
     * 检查当前学年是否存在，
     * 如果不存在，则插入
     * @param schoolYear
     */
    public void checkCurrentSchoolYear(String schoolYear) {
        SchoolYearData syData = new SchoolYearData();
        syData.setId(null);
        syData.setSchoolyearname(schoolYear);

        List<SchoolYearData> schoolYearDataList = sydMapper.select(syData);
        if(schoolYearDataList.size() == 0) {
            sydMapper.insert(syData);
        }
    }
}
