package com.wit.paperadmin.model;

import javax.persistence.*;

@Table(name = "paper_status")
public class PaperStatusData {
    /**
     * 试卷状态ID
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 试卷状态名称
     */
    @Column(name = "statusName")
    private String statusname;

    /**
     * 获取试卷状态ID
     *
     * @return ID - 试卷状态ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置试卷状态ID
     *
     * @param id 试卷状态ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取试卷状态名称
     *
     * @return statusName - 试卷状态名称
     */
    public String getStatusname() {
        return statusname;
    }

    /**
     * 设置试卷状态名称
     *
     * @param statusname 试卷状态名称
     */
    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }
}