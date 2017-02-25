package com.wit.paperadmin.model;

import javax.persistence.*;

@Table(name = "paperinfo")
public class PaperInfoData {
    /**
     * 试卷ID
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 课程名
     */
    private String course;

    /**
     * 学年ID
     */
    @Column(name = "schoolYearID")
    private Integer schoolyearid;

    /**
     * 学期
     */
    private String term;

    /**
     * 学院ID
     */
    @Column(name = "instituteID")
    private Integer instituteid;

    /**
     * 专业ID
     */
    @Column(name = "profession")
    private String profession;

    /**
     * 试卷份数
     */
    @Column(name = "paperNumber")
    private Integer papernumber;

    /**
     * 答题纸份数
     */
    @Column(name = "answersheetNumber")
    private Integer answersheetnumber;

    /**
     * 草稿纸份数
     */
    @Column(name = "scratchpaperNumber")
    private Integer scratchpapernumber;

    /**
     * 试卷类型
     */
    @Column(name = "paperType")
    private String papertype;

    @Column(name = "testTime")
    private Integer testtime;

    @Column(name = "uploadTime")
    private Integer uploadtime;

    /**
     * 试卷状态, 0: 未打印 1: 打印中 2: 待取
     */
    @Column(name = "paperStatus")
    private Integer paperstatus;

    /**
     * 用户ID
     */
    @Column(name = "userID")
    private Integer userid;

    /**
     * 保存路径
     */
    private String saveaddress;

    /**
     * 取卷人
     */
    @Column(name = "receivePerson")
    private String receiveperson;

    /**
     * 试卷存放位置
     */
    @Column(name = "saveLocation")
    private String savelocation;

    /**
     * 取卷时间
     */
    @Column(name = "receiveTime")
    private Integer receivetime;

    public String getReceiveperson() {
        return receiveperson;
    }

    public void setReceiveperson(String receiveperson) {
        this.receiveperson = receiveperson;
    }

    public Integer getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(Integer receivetime) {
        this.receivetime = receivetime;
    }

    public String getSavelocation() {
        return savelocation;
    }

    public void setSavelocation(String savelocation) {
        this.savelocation = savelocation;
    }

    /**
     * 获取试卷ID
     *
     * @return ID - 试卷ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置试卷ID
     *
     * @param id 试卷ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取课程名
     *
     * @return course - 课程名
     */
    public String getCourse() {
        return course;
    }

    /**
     * 设置课程名
     *
     * @param course 课程名
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * 获取学年ID
     *
     * @return schoolYearID - 学年ID
     */
    public Integer getSchoolyearid() {
        return schoolyearid;
    }

    /**
     * 设置学年ID
     *
     * @param schoolyearid 学年ID
     */
    public void setSchoolyearid(Integer schoolyearid) {
        this.schoolyearid = schoolyearid;
    }

    /**
     * 获取学期
     *
     * @return term - 学期
     */
    public String getTerm() {
        return term;
    }

    /**
     * 设置学期
     *
     * @param term 学期
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * 获取学院ID
     *
     * @return instituteID - 学院ID
     */
    public Integer getInstituteid() {
        return instituteid;
    }

    /**
     * 设置学院ID
     *
     * @param instituteid 学院ID
     */
    public void setInstituteid(Integer instituteid) {
        this.instituteid = instituteid;
    }

    /**
     * 获取专业ID
     *
     * @return professionID - 专业ID
     */
    public String getProfession() {
        return profession;
    }

    /**
     * 设置专业ID
     *
     * @param profession 专业ID
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }

    /**
     * 获取试卷份数
     *
     * @return paperNumber - 试卷份数
     */
    public Integer getPapernumber() {
        return papernumber;
    }

    /**
     * 设置试卷份数
     *
     * @param papernumber 试卷份数
     */
    public void setPapernumber(Integer papernumber) {
        this.papernumber = papernumber;
    }

    /**
     * 获取答题纸份数
     *
     * @return answersheetNumber - 答题纸份数
     */
    public Integer getAnswersheetnumber() {
        return answersheetnumber;
    }

    /**
     * 设置答题纸份数
     *
     * @param answersheetnumber 答题纸份数
     */
    public void setAnswersheetnumber(Integer answersheetnumber) {
        this.answersheetnumber = answersheetnumber;
    }

    /**
     * 获取草稿纸份数
     *
     * @return scratchpaperNumber - 草稿纸份数
     */
    public Integer getScratchpapernumber() {
        return scratchpapernumber;
    }

    /**
     * 设置草稿纸份数
     *
     * @param scratchpapernumber 草稿纸份数
     */
    public void setScratchpapernumber(Integer scratchpapernumber) {
        this.scratchpapernumber = scratchpapernumber;
    }

    /**
     * 获取试卷类型
     *
     * @return paperType - 试卷类型
     */
    public String getPapertype() {
        return papertype;
    }

    /**
     * 设置试卷类型
     *
     * @param papertype 试卷类型
     */
    public void setPapertype(String papertype) {
        this.papertype = papertype;
    }

    /**
     * @return testTime
     */
    public Integer getTesttime() {
        return testtime;
    }

    /**
     * @param testtime
     */
    public void setTesttime(Integer testtime) {
        this.testtime = testtime;
    }

    /**
     * @return uploadTime
     */
    public Integer getUploadtime() {
        return uploadtime;
    }

    /**
     * @param uploadtime
     */
    public void setUploadtime(Integer uploadtime) {
        this.uploadtime = uploadtime;
    }

    /**
     * 获取试卷状态, 0: 未打印 1: 打印中 2: 待取
     *
     * @return paperStatus - 试卷状态, 0: 未打印 1: 打印中 2: 待取
     */
    public Integer getPaperstatus() {
        return paperstatus;
    }

    /**
     * 设置试卷状态, 0: 未打印 1: 打印中 2: 待取
     *
     * @param paperstatus 试卷状态, 0: 未打印 1: 打印中 2: 待取
     */
    public void setPaperstatus(Integer paperstatus) {
        this.paperstatus = paperstatus;
    }

    /**
     * 获取用户ID
     *
     * @return userID - 用户ID
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置用户ID
     *
     * @param userid 用户ID
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取保存路径
     *
     * @return saveaddress - 保存路径
     */
    public String getSaveaddress() {
        return saveaddress;
    }

    /**
     * 设置保存路径
     *
     * @param saveaddress 保存路径
     */
    public void setSaveaddress(String saveaddress) {
        this.saveaddress = saveaddress;
    }
}