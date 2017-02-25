package com.wit.paperadmin.model;

import javax.persistence.*;

@Table(name = "paper_aeskey")
public class PaperKeyData {
    /**
     * 密钥ID
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 密钥内容
     */
    @Column(name = "keyContent")
    private String keycontent;

    /**
     * 试卷ID
     */
    @Column(name = "paperID")
    private Integer paperid;

    /**
     * 获取密钥ID
     *
     * @return ID - 密钥ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置密钥ID
     *
     * @param id 密钥ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取密钥内容
     *
     * @return keyContent - 密钥内容
     */
    public String getKeycontent() {
        return keycontent;
    }

    /**
     * 设置密钥内容
     *
     * @param keycontent 密钥内容
     */
    public void setKeycontent(String keycontent) {
        this.keycontent = keycontent;
    }

    /**
     * 获取试卷ID
     *
     * @return paperID - 试卷ID
     */
    public Integer getPaperid() {
        return paperid;
    }

    /**
     * 设置试卷ID
     *
     * @param paperid 试卷ID
     */
    public void setPaperid(Integer paperid) {
        this.paperid = paperid;
    }
}