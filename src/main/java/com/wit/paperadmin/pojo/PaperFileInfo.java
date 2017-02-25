package com.wit.paperadmin.pojo;

import java.io.File;

/**
 * Created by 何鹏帅 on 2016/12/29.
 */
public class PaperFileInfo {
    private String filename;
    private File file;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
