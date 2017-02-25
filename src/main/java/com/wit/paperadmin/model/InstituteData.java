package com.wit.paperadmin.model;

import javax.persistence.*;

@Table(name = "institute")
public class InstituteData {
    /**
     * 学院ID
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 学院名称
     */
    @Column(name = "instituteName")
    private String institutename;

    /**
     * 获取学院ID
     *
     * @return ID - 学院ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置学院ID
     *
     * @param id 学院ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取学院名称
     *
     * @return instituteName - 学院名称
     */
    public String getInstitutename() {
        return institutename;
    }

    /**
     * 设置学院名称
     *
     * @param institutename 学院名称
     */
    public void setInstitutename(String institutename) {
        this.institutename = institutename;
    }
}