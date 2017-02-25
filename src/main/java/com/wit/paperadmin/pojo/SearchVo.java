package com.wit.paperadmin.pojo;

/**
 * Created by 何鹏帅 on 2017/1/5.
 */
public class SearchVo {

    /**
     * 学年id
     */
    private int schoolyearid;

    /**
     * 学期
     */
    private String term;

    /**
     * 学院id
     */
    private Integer instituteid;

    /**
     * 学院
     */
    private String institute;

    /**
     * 专业
     */
    private String profession;

    /**
     * 状态id
     */
    private Integer paperstatusid;

    /**
     * 状态
     */
    private String paperstatus;

    public int getSchoolyearid() {
        return schoolyearid;
    }

    public void setSchoolyearid(int schoolyearid) {
        this.schoolyearid = schoolyearid;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Integer getInstituteid() {
        return instituteid;
    }

    public void setInstituteid(Integer instituteid) {
        this.instituteid = instituteid;
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

    public Integer getPaperstatusid() {
        return paperstatusid;
    }

    public void setPaperstatusid(Integer paperstatusid) {
        this.paperstatusid = paperstatusid;
    }

    public String getPaperstatus() {
        return paperstatus;
    }

    public void setPaperstatus(String paperstatus) {
        this.paperstatus = paperstatus;
    }
}
