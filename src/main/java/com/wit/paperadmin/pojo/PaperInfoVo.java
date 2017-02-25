package com.wit.paperadmin.pojo;

import com.wit.paperadmin.model.PaperInfoData;

/**
 * Created by 何鹏帅 on 2016/12/29.
 */
public class PaperInfoVo extends PaperInfoData {
    private String schoolyear;
    private String institute;
    private String profession;
    private String status;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSchoolyear() {
        return schoolyear;
    }

    public void setSchoolyear(String schoolyear) {
        this.schoolyear = schoolyear;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
