package com.tudou.crm.storage.inStorage.mapper;

import com.tudou.crm.storage.inStorage.entity.InStorageWorkPart;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface InStorageWorkPartMapper extends Mapper<InStorageWorkPart> {
    /**
     * 通过入库单编号查询明细
     *
     * @param id
     * @return
     */
    List<InStorageWorkPart> findInStorageWorkPartByStorageId(String id);


}