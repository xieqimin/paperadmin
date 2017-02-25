package com.wit.paperadmin.model;

import javax.persistence.*;

@Table(name = "school_year")
public class SchoolYearData {
    /**
     * 学年ID
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 学年名称
     */
    @Column(name = "schoolYearName")
    private String schoolyearname;

    /**
     * 获取学年ID
     *
     * @return ID - 学年ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置学年ID
     *
     * @param id 学年ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取学年名称
     *
     * @return schoolYearName - 学年名称
     */
    public String getSchoolyearname() {
        return schoolyearname;
    }

    /**
     * 设置学年名称
     *
     * @param schoolyearname 学年名称
     */
    public void setSchoolyearname(String schoolyearname) {
        this.schoolyearname = schoolyearname;
    }
}