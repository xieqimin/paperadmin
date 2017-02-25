package com.wit.paperadmin.mapper;

import com.wit.paperadmin.model.PaperKeyData;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface PaperKeyDataMapper extends Mapper<PaperKeyData> {
    String getKeyByPaperID(int paperID);
}